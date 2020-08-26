package com.enigma.mybel.controllers;

import com.enigma.mybel.constants.Constant;
import com.enigma.mybel.entity.DesignInterior;
import com.enigma.mybel.services.DesignInteriorService;
import com.enigma.mybel.services.FileUtil;
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
@RequestMapping("/design")
public class DesignInteriorController {
    @Autowired
    DesignInteriorService service;

    @Autowired
    FileUtil fileUtil;

    @PostMapping
    DesignInterior saveDesign(@RequestPart MultipartFile file, @RequestPart String unit) throws IOException {
        return service.saveDesignInterior(file,unit);
    }

    @PostMapping("/form")
    DesignInterior saveFormDesign(@RequestBody DesignInterior interior){
        return service.saveFormDesign(interior);
    }

    @PutMapping
    DesignInterior updateDesign(@RequestPart MultipartFile file,@RequestPart String unit) throws IOException {
        return service.updateDesignInterior(file,unit);
    }

    @GetMapping("/{id}")
    DesignInterior getDesignById(@PathVariable String id){
        return service.getDesignInterior(id);
    }

    @GetMapping("/list")
    List<DesignInterior> getAllDesign(@RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "size",defaultValue = "100") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.getAllDesignInterior();
    }

    @DeleteMapping("/{id}")
    void deleteDesign(@PathVariable String id){
        service.deleteDesignInterior(id);
    }

    @GetMapping("/photo/{path}")
    ResponseEntity<Resource> getDesignPhoto(@PathVariable String path, HttpServletRequest request){
        DesignInterior designInterior=service.getDesignInterior(path);
        if(designInterior==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(Constant.FILENOTFOUND));
        Resource resource = fileUtil.read(designInterior.getId());
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
