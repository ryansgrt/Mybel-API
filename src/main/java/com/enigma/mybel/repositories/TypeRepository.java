package com.enigma.mybel.repositories;

import com.enigma.mybel.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,String> {
    Type findByNameEquals (String name);
}
