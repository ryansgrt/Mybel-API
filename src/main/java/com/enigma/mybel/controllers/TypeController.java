package com.enigma.mybel.controllers;

import com.enigma.mybel.entity.Type;
import com.enigma.mybel.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    TypeService service;

    @PostMapping
    Type saveType(@RequestBody Type type){
        return service.saveType(type);
    }

    @PutMapping
    Type updateType(@RequestBody Type type){
        return service.updateType(type);
    }

    @GetMapping("/{id}")
    Type getTypeById(@PathVariable String id){
        return service.getType(id);
    }

    @GetMapping("/name/{name}")
    Type getTypeByName(@PathVariable String name){
        return service.getTypeByName(name);
    }

    @GetMapping("/list")
    List<Type> getAllType(){
        return service.getAllType();
    }

    @DeleteMapping
    void deleteType(String id){
        service.deleteType(id);
    }
}
