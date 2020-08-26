package com.enigma.mybel.repositories;

import com.enigma.mybel.entity.DesignInterior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignInteriorRepository extends JpaRepository<DesignInterior,String> {
}
