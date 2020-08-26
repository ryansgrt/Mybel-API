package com.enigma.mybel.repositories;

import com.enigma.mybel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    List<User> findAllByUsernameEquals (String username);
    List<User> findAllByEmailEquals (String email);
    User findByUsernameEqualsAndPasswordEquals(String username,String password);
    User findByEmailEqualsAndPasswordEquals(String email,String password);
}
