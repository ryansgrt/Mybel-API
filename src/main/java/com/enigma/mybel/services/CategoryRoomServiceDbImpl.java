package com.enigma.mybel.services;

import com.enigma.mybel.entity.CategoryRoom;
import com.enigma.mybel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryRoomServiceDbImpl implements CategoryRoomService{
    @Autowired
    RoomRepository repository;

    @Override
    public CategoryRoom saveCategory(CategoryRoom room) {
        return repository.save(room);
    }

    @Override
    public CategoryRoom updateCategory(CategoryRoom room) {
        return repository.save(room);
    }

    @Override
    public List<CategoryRoom> getAllCategory() {
        return repository.findAll();
    }

    @Override
    public CategoryRoom getCategoryByName(String name) {
        return repository.findByNameEquals(name);
    }

    @Override
    public void deleteCategory(String id) {
        repository.deleteById(id);
    }
}