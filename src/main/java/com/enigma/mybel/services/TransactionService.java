package com.enigma.mybel.services;

import com.enigma.mybel.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction (Transaction transaction);
    List<Transaction> getAllTransaction ();
    void deleteTransaction (String id);
}
