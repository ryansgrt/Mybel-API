package com.enigma.mybel.services;

import com.enigma.mybel.constants.Constant;
import com.enigma.mybel.entity.Vendor;
import com.enigma.mybel.exceptions.FileNotFoundException;
import com.enigma.mybel.repositories.VendorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VendorServiceDbImpl implements VendorService{
    @Autowired
    VendorRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FileUtil fileUtil;

    @Override
    public Vendor saveVendor(MultipartFile file,String requestBody) throws IOException {
        Vendor vendor = repository.save(objectMapper.readValue(requestBody,Vendor.class));

        if(file!=null) vendor.setPhoto(String.format(Constant.PATHVENDOR,fileUtil.upload(vendor.getId(),file)));

        repository.save(vendor);
        return vendor;
    }

    @Override
    public Vendor saveFormVendor(Vendor vendor) {
        return repository.save(vendor);
    }

    @Override
    public Vendor updateVendor(MultipartFile file,String requestBody) throws IOException {
        Vendor vendor = repository.save(objectMapper.readValue(requestBody,Vendor.class));

        if(file!=null) vendor.setPhoto(String.format(Constant.PATHVENDOR,fileUtil.upload(vendor.getId(),file)));

        return repository.save(vendor);
    }

    @Override
    public Vendor getVendor(String id) {
        if(repository.findById(id).isPresent()) {
            Vendor vendor = repository.findById(id).get();
            return vendor;
        }else throw new FileNotFoundException(id);
    }

    @Override
    public Vendor signInVendor(String identity, String password) {
        Vendor searchUsername = repository.findByUsernameEqualsAndPasswordEquals(identity,password);
        Vendor searchEmail = repository.findByEmailEqualsAndPasswordEquals(identity,password);

        if(searchUsername!=null){
            return searchUsername;
        }else if(searchEmail!=null){
            return searchEmail;
        }else throw new FileNotFoundException("selected");
    }


    @Override
    public Page<Vendor> getAllVendor(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Vendor> getUsername(String keyword) {
        return repository.findAllByUsernameEquals(keyword);
    }

    @Override
    public List<Vendor> getEmail(String keyword) {
        return repository.findAllByEmailEquals(keyword);
    }

    @Override
    public void deleteVendor(String id) {
        repository.deleteById(id);
    }
}