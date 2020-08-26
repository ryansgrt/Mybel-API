package com.enigma.mybel.controllers;

import com.enigma.mybel.constants.Constant;
import com.enigma.mybel.entity.Vendor;
import com.enigma.mybel.services.FileUtil;
import com.enigma.mybel.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    VendorService service;

    @Autowired
    FileUtil fileUtil;

    @PostMapping
    Vendor saveVendor(@RequestPart MultipartFile file,@RequestPart String vendor) throws IOException {
        return service.saveVendor(file,vendor);
    }

    @PostMapping("/")
    Vendor saveFormVendor(@RequestBody Vendor vendor){
        return service.saveFormVendor(vendor);
    }

    @GetMapping("/signin")
    Vendor signIn(@RequestParam(name = "identity") String identity,@RequestParam(name = "password") String password){
        return service.signInVendor(identity,password);
    }

    @PutMapping
    Vendor updateVendor(@RequestPart MultipartFile file,@RequestPart String vendor) throws IOException {
        return service.updateVendor(file,vendor);
    }

    @GetMapping("/{id}")
    Vendor getVendorById(@PathVariable String id){
        return service.getVendor(id);
    }

    @GetMapping("/list")
    Page<Vendor> getAllVendor(@RequestParam(value = "page",defaultValue = "0") Integer page,@RequestParam(value = "size",defaultValue = "100") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.getAllVendor(pageable);
    }

    @GetMapping("/username/{keyword}")
    List<Vendor> getVendorByUsername(@PathVariable String keyword){
        return service.getUsername(keyword);
    }

    @GetMapping("/email/{keyword}")
    List<Vendor> getVendorByEmail(@PathVariable String keyword){
        return service.getEmail(keyword);
    }

    @DeleteMapping("/{id}")
    void deleteVendor(@PathVariable String id){
        service.deleteVendor(id);
    }

    @GetMapping("/photo/{path}")
    ResponseEntity<Resource> getVendorPhoto(@PathVariable String path, HttpServletRequest request){
        Vendor vendor=service.getVendor(path);
        if(vendor==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(Constant.FILENOTFOUND));
        Resource resource = fileUtil.read(vendor.getId());
        String contentType =null;

        try {
            contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(Constant.FILENOTFOUND));
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename\""+resource.getFilename()+"\"")
                .body(resource);
    }
}
