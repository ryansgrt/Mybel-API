package com.enigma.mybel.controllers;

import com.enigma.mybel.entity.Transaction;
import com.enigma.mybel.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    @Autowired
    TransactionService service;

    @PostMapping
    Transaction saveTransaction (@RequestBody Transaction transaction){
        return service.saveTransaction(transaction);
    }

    @GetMapping("/list")
    List<Transaction> getAllTransaction (){
        return service.getAllTransaction();
    }

    @DeleteMapping("/{id}")
    void deleteTransaction(@PathVariable String id){
        service.deleteTransaction(id);
    }

}
