package com.enigma.mybel.controllers;

import com.enigma.mybel.entity.CategoryRoom;
import com.enigma.mybel.services.CategoryRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class CategoryRoomController {
    @Autowired
    CategoryRoomService service;

    @PostMapping
    CategoryRoom saveCategory(@RequestBody CategoryRoom room) {
        return service.saveCategory(room);
    }

    @PutMapping
    CategoryRoom updateCategory(@RequestBody CategoryRoom room) {
        return service.updateCategory(room);
    }

    @GetMapping("/list")
    List<CategoryRoom> getAllCategory() {
        return service.getAllCategory();
    }

    @GetMapping("/{name}")
    CategoryRoom getCategoryByName(@PathVariable String name) {
        return service.getCategoryByName(name);
    }

    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable String id) {
        service.deleteCategory(id);
    }
}