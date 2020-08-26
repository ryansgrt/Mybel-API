package com.enigma.mybel.services;

import com.enigma.mybel.entity.*;
import com.enigma.mybel.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceDbImpl implements TransactionService{
    @Autowired
    TransactionRepository repository;

    @Autowired
    DesignInteriorService designInteriorService;

    @Autowired
    UnitService unitService;

    @Autowired
    UserService userService;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        List<DesignInterior> interiorList = new ArrayList<DesignInterior>();
        List<Unit> unitList = new ArrayList<Unit>();

        if(transaction.getDesign()!=null){
            DesignInterior designInterior = designInteriorService.getDesignInterior(transaction.getDesign());
            interiorList.add(designInterior);
            transaction.setDesignInteriors(interiorList);
        }
        if (transaction.getUnit()!=null){
            Unit unit = unitService.getUnit(transaction.getUnit());
            unitList.add(unit);
            transaction.setUnits(unitList);
        }

        User user = userService.getUser(transaction.getSelectUser());
        transaction.setUser(user);

        return repository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return repository.findAll();
    }

    @Override
    public void deleteTransaction(String id) {
        repository.deleteById(id);
    }
}
