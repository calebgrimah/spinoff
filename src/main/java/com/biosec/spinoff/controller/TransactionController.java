package com.biosec.spinoff.controller;
import com.biosec.spinoff.EmailCfg;
import com.biosec.spinoff.model.*;
import com.biosec.spinoff.repository.CaseMsREpository;
import com.biosec.spinoff.repository.EmployeeRepository;
import com.biosec.spinoff.repository.EmployerRepository;
import com.biosec.spinoff.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TransactionController {

    private TransactionRepository repository;
    private EmployerRepository employerRepository;
    private EmployeeRepository employeeRepository;
    private CaseMsREpository caseMsREpository;
    private EmailCfg emailCfg;

    public TransactionController(TransactionRepository repository,
                                 EmployerRepository employerRepository,
                                 EmployeeRepository employeeRepository,
                                 CaseMsREpository caseMsREpository, EmailCfg emailCfg) {
        this.repository = repository;
        this.employerRepository = employerRepository;
        this.employeeRepository = employeeRepository;
        this.caseMsREpository = caseMsREpository;
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
            if(transaction.getPaymentStatus() == 1){
                //successful
                Optional<Employer> byEmployerId = employerRepository.findByEmployerId(transaction.getEmployerId());
                if(byEmployerId.isPresent()){
                //send mail here
                    Optional<Employee> byEmployeeId = employeeRepository.findByEmployeeId(transaction.getEmployeeId());
                    if(byEmployeeId.isPresent()){
                        //employee is present
                        Employee employee = byEmployeeId.get();
                        //check the field for the integer value and return the values as expected
                        if(employee.getCriminalValue() == 1){
                            CriminalRecordClass criminalRecordClass = new CriminalRecordClass();
                            criminalRecordClass.setEmployee(employee);
                            CaseMS caseMS = caseMsREpository.findByEnrollmentID("456").get();
                            criminalRecordClass.setCriminalData(caseMS);
                            sendFeedback(new Feedback("Individual with name " + employee.getFirstname() + " " + employee.getLastname() + " has a criminal record"
                                    + /*" has criminal Record with the police" + " \n . The details of the record are as follows: \n " +
                                    "Number of Convictions: " + caseMS.getNumberOfConvictions() + " \n " +
                                    "\t Offence : " + caseMS.getConvictions().get(0).getOffence() + "\n " +
                                    "\t Offence: " + caseMS.getConvictions().get(0).getOffence() + "\n\n" +*/ "Thank You","police@email.com",criminalRecordClass),byEmployerId.get().getEmail());
                            return new ResponseEntity<>(criminalRecordClass,HttpStatus.OK);
                        }else if (employee.getCriminalValue() == 2){
                            CriminalRecordClass criminalRecordClass = new CriminalRecordClass();
                            criminalRecordClass.setEmployee(employee);
                            criminalRecordClass.setCriminalData(null);
                            sendFeedback(new Feedback("Individual with name "+ employee.getFirstname() + employee.getLastname() + " has no criminal Record", "police@email.com",criminalRecordClass),byEmployerId.get().getEmail());
                            return new ResponseEntity<>(criminalRecordClass,HttpStatus.OK);
                        }else {
                            sendFeedback(new Feedback("This has been Verified with no police record","police@email.com",null),byEmployerId.get().getEmail());
                            return new ResponseEntity<>(HttpStatus.ACCEPTED);
                        }
                    }else {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }


                }
            }
            return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
        }
        //also check if the employee has paid successfully
        //return email to employer on the status.
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void sendFeedback(Feedback feedback, String employerMail){
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
        mailMessage.setTo(employerMail);
        mailMessage.setSubject("Response from background check");
        mailMessage.setText(feedback.getMessage());


        // Send mail
        mailSender.send(mailMessage);
    }

}
