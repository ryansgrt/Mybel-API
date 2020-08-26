package com.enigma.mybel.services;

import com.enigma.mybel.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VendorService {
    Vendor saveVendor(MultipartFile file, String requestBody) throws IOException;
    Vendor saveFormVendor(Vendor vendor);
    Vendor updateVendor(MultipartFile file,String requestBody) throws IOException;
    Vendor getVendor(String id);
    Vendor signInVendor(String identity,String password);
    Page<Vendor> getAllVendor(Pageable pageable);
    List<Vendor> getUsername(String keyword);
    List<Vendor> getEmail(String keyword);
    void deleteVendor(String id);
}