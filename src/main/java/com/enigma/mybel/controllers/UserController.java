package com.enigma.mybel.controllers;

import com.enigma.mybel.constants.Constant;
import com.enigma.mybel.entity.User;
import com.enigma.mybel.services.FileUtil;
import com.enigma.mybel.services.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    FileUtil fileUtil;

    @PostMapping
    User saveUser(@RequestPart MultipartFile file, @RequestPart String user) throws IOException {
        return service.saveUser(file,user);
    }

    @PostMapping("/")
    User saveFormUser(@RequestBody User user){
        return service.saveFormUser(user);
    }

    @GetMapping("/signin")
    User signIn(@RequestParam(name = "identity") String identity,@RequestParam(name = "password") String password){
        return service.signInUser(identity,password);
    }

    @PutMapping
    User updateUser(@RequestPart MultipartFile file,@RequestPart String user) throws IOException {
        return service.updateUser(file,user);
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable String id){
        return service.getUser(id);
    }

    @GetMapping("/list")
    Page<User> getAllUser(@RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "size",defaultValue = "100") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.getAllUser(pageable);
    }

    @GetMapping("/username/{keyword}")
    List<User> getUserByUsername(@PathVariable String keyword){
        return service.getUsername(keyword);
    }

    @GetMapping("/email/{keyword}")
    List<User> getUserByEmail(@PathVariable String keyword){
        return service.getEmail(keyword);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable String id){
        service.deleteUser(id);
    }

    @GetMapping("/photo/{path}")
    ResponseEntity<Resource> getUserPhoto(@PathVariable String path, HttpServletRequest request){
        User user=service.getUser(path);
        if(user==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(Constant.FILENOTFOUND));
        Resource resource = fileUtil.read(user.getId());
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