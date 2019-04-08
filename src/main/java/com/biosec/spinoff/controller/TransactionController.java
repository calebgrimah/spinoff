package com.biosec.spinoff.controller;
import com.biosec.spinoff.EmailCfg;
import com.biosec.spinoff.model.Employee;
import com.biosec.spinoff.model.Employer;
import com.biosec.spinoff.model.Feedback;
import com.biosec.spinoff.model.Transaction;
import com.biosec.spinoff.repository.EmployeeRepository;
import com.biosec.spinoff.repository.EmployerRepository;
import com.biosec.spinoff.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TransactionController {

    private TransactionRepository repository;
    private EmployerRepository employerRepository;
    private EmployeeRepository employeeRepository;
    private EmailCfg emailCfg;

    public TransactionController(TransactionRepository repository,
                                 EmployerRepository employerRepository,
                                 EmployeeRepository employeeRepository,
                                 EmailCfg emailCfg) {
        this.repository = repository;
        this.employerRepository = employerRepository;
        this.employeeRepository = employeeRepository;
        this.emailCfg = emailCfg;
    }

    @PostMapping("spinoff/transaction")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction){
        repository.save(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("spinoff/test")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("Hooray",HttpStatus.OK);
    }



    @GetMapping("spinoff/verification/{transactionId}")
    public ResponseEntity<?> verifyTransaction(@PathVariable String transactionId){
        //fetch user and check if employee has a transaction
        Optional<Transaction> byTransactionId = repository.findByTransactionId(transactionId);
        if(byTransactionId.isPresent()){
            Transaction transaction = byTransactionId.get();

            if(transaction.isTransactionSuccessful()){
                Optional<Employer> byEmployerId = employerRepository.findByEmployerId(transaction.getEmployerId());
                if(byEmployerId.isPresent()){
                //send mail here
                    Optional<Employee> byEmployeeId = employeeRepository.findByEmployeeId(transaction.getEmployeeId());
                    if(byEmployeeId.isPresent()){
                        sendFeedback(new Feedback("This has been Verified with no Criminal Data","policeCriminalDb@policenpf.com",byEmployeeId.get()));
                        return new ResponseEntity<>(HttpStatus.OK);
                    }

                }
            }
            return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
        }
        //also check if the employee has paid successfully
        //return email to employer on the status.
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void sendFeedback(Feedback feedback){
        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());
//
//        MimeMailMessage mimeMailMessage =  new MimeMailMessage(mailSender.createMimeMessage());
//        mimeMailMessage.
        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("lamahgrimah@gmail.com");
        mailMessage.setSubject("New feedback from spinoff project");
        mailMessage.setText(feedback.getEmployee().getFirstname() + " is not in the police Database");


        // Send mail
        mailSender.send(mailMessage);
    }

}
