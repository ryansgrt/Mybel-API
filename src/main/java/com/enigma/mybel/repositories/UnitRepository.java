package com.enigma.mybel.repositories;

import com.enigma.mybel.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit,String> {
}
