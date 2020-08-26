package com.enigma.mybel.services;

import com.enigma.mybel.entity.Type;

import java.util.List;

public interface TypeService {
    Type saveType(Type type) ;
    Type updateType(Type type) ;
    Type getType(String id);
    Type getTypeByName(String name);
    List<Type> getAllType();
    void deleteType(String id);
}
