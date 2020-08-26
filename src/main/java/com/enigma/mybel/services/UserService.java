package com.enigma.mybel.services;

import com.enigma.mybel.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User saveUser(MultipartFile file, String requestBody) throws IOException;
    User saveFormUser(User user);
    User updateUser(MultipartFile file,String requestBody) throws IOException;
    User getUser(String id);
    User signInUser(String identity,String password);
    Page<User> getAllUser(Pageable pageable);
    List<User> getUsername(String keyword);
    List<User> getEmail(String keyword);
    void deleteUser(String id);
}
