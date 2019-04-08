package com.biosec.spinoff.repository;


import com.biosec.spinoff.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionId(String transactionId);
}
