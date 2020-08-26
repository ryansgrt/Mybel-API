package com.enigma.mybel.services;

import com.enigma.mybel.entity.Type;
import com.enigma.mybel.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceDbImpl implements TypeService {
    @Autowired
    TypeRepository repository;

    @Override
    public Type saveType(Type type) {
        return repository.save(type);
    }

    @Override
    public Type updateType(Type type) {
        return repository.save(type);
    }

    @Override
    public Type getType(String id) {
        Type type = repository.findById(id).get();
        return type;
    }

    @Override
    public Type getTypeByName(String name) {
        Type type=repository.findByNameEquals(name);
        return type;
    }

    @Override
    public List<Type> getAllType() {
        return repository.findAll();
    }

    @Override
    public void deleteType(String id) {
        repository.deleteById(id);
    }
}