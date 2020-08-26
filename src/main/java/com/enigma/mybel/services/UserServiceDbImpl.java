package com.enigma.mybel.services;

import com.enigma.mybel.constants.Constant;
import com.enigma.mybel.entity.User;
import com.enigma.mybel.exceptions.FileNotFoundException;
import com.enigma.mybel.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceDbImpl implements UserService{
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    UserRepository repository;


    @Override
    public User saveUser(MultipartFile file, String requestBody) throws IOException {
        User user = repository.save(objectMapper.readValue(requestBody,User.class));

        if(file!=null) user.setPhoto(String.format(Constant.PATHUSER,fileUtil.upload(user.getId(),file)));

        repository.save(user);
        return user;
    }

    @Override
    public User saveFormUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(MultipartFile file, String requestBody) throws IOException {
        User user = repository.save(objectMapper.readValue(requestBody,User.class));

        if(file!=null) user.setPhoto(String.format(Constant.PATHUSER,fileUtil.upload(user.getId(),file)));

        repository.save(user);
        return user;
    }

    @Override
    public User getUser(String id) {
        if(repository.findById(id).isPresent()) {
            User user = repository.findById(id).get();
            return user;
        }else throw new FileNotFoundException(id);
    }

    @Override
    public User signInUser(String identity, String password) {
        User searchUsername = repository.findByUsernameEqualsAndPasswordEquals(identity,password);
        User searchEmail = repository.findByEmailEqualsAndPasswordEquals(identity,password);

        if(searchUsername!=null){
            return searchUsername;
        }else if(searchEmail!=null){
            return searchEmail;
        }else throw new FileNotFoundException("selected");
    }

    @Override
    public Page<User> getAllUser(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<User> getUsername(String keyword) {
        return repository.findAllByUsernameEquals(keyword);
    }

    @Override
    public List<User> getEmail(String keyword) {
        return repository.findAllByEmailEquals(keyword);
    }

    @Override
    public void deleteUser(String id) {
        repository.deleteById(id);
    }
}