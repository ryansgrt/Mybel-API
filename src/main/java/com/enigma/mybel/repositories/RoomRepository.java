package com.enigma.mybel.repositories;

import com.enigma.mybel.entity.CategoryRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<CategoryRoom,String> {
    CategoryRoom findByNameEquals (String name);
}
