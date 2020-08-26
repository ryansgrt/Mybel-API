package com.enigma.mybel.services;

import com.enigma.mybel.entity.CategoryRoom;

import java.util.List;

public interface CategoryRoomService {
    CategoryRoom saveCategory(CategoryRoom room);
    CategoryRoom updateCategory(CategoryRoom room);
    List<CategoryRoom> getAllCategory();
    CategoryRoom getCategoryByName(String name);
    void deleteCategory (String id);
}
