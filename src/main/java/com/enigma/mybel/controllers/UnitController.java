package com.enigma.mybel.controllers;

import com.enigma.mybel.constants.Constant;
import com.enigma.mybel.entity.Unit;
import com.enigma.mybel.services.FileUtil;
import com.enigma.mybel.services.UnitService;
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

@RestController
@RequestMapping("/unit")
public class UnitController {
    @Autowired
    UnitService service;

    @Autowired
    FileUtil fileUtil;

    @PostMapping
    Unit saveUnit(@RequestPart MultipartFile file, @RequestPart String unit) throws IOException {
        return service.saveUnit(file,unit);
    }

    @PostMapping("/form")
    Unit saveFormUnit(@RequestBody Unit unit){
        return service.saveFormUnit(unit);
    }

    @PutMapping
    Unit updateUnit(@RequestPart MultipartFile file,@RequestPart String unit) throws IOException {
        return service.updateUnit(file,unit);
    }

    @GetMapping("/{id}")
    Unit getUnitById(@PathVariable String id){
        return service.getUnit(id);
    }

    @GetMapping("/list")
    Page<Unit> getAllUnit(@RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "size",defaultValue = "100") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.getAllUnit(pageable);
    }

    @DeleteMapping("/{id}")
    void deleteUnit(@PathVariable String id){
        service.deleteUnit(id);
    }

    @GetMapping("/photo/{path}")
    ResponseEntity<Resource> getUnitPhoto(@PathVariable String path, HttpServletRequest request){
        Unit unit=service.getUnit(path);
        if(unit==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(Constant.FILENOTFOUND));
        Resource resource = fileUtil.read(unit.getId());
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