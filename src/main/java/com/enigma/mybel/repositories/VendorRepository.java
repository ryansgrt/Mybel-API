package com.enigma.mybel.repositories;

import com.enigma.mybel.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor,String> {
    List<Vendor> findAllByUsernameEquals (String username);
    List<Vendor> findAllByEmailEquals (String email);
    Vendor findByUsernameEqualsAndPasswordEquals(String username,String password);
    Vendor findByEmailEqualsAndPasswordEquals(String email,String password);
}
