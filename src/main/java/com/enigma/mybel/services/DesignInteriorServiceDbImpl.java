package com.enigma.mybel.services;

import com.enigma.mybel.constants.Constant;
import com.enigma.mybel.entity.CategoryRoom;
import com.enigma.mybel.entity.DesignInterior;
import com.enigma.mybel.entity.Vendor;
import com.enigma.mybel.exceptions.FileNotFoundException;
import com.enigma.mybel.repositories.DesignInteriorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DesignInteriorServiceDbImpl implements DesignInteriorService{
    @Autowired
    DesignInteriorRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    VendorService vendorService;

    @Autowired
    CategoryRoomService roomService;

    @Override
    public DesignInterior saveDesignInterior(MultipartFile file, String requestBody) throws IOException {
        DesignInterior designInterior = repository.save(objectMapper.readValue(requestBody,DesignInterior.class));
        Vendor vendor=vendorService.getVendor(designInterior.getIdVendor());
        CategoryRoom room = roomService.getCategoryByName(designInterior.getRoomName());

        if(file!=null) designInterior.setPicture(String.format(Constant.PATHDESIGN,fileUtil.upload(designInterior.getId(),file)));

        designInterior.setRoom(room);
        designInterior.setVendor(vendor);

        return repository.save(designInterior);
    }

    @Override
    public DesignInterior saveFormDesign(DesignInterior interior) {
        return repository.save(interior);
    }

    @Override
    public DesignInterior updateDesignInterior(MultipartFile file, String requestBody) throws IOException {
        DesignInterior designInterior = repository.save(objectMapper.readValue(requestBody,DesignInterior.class));

        if(file!=null) designInterior.setPicture(String.format(Constant.PATHDESIGN,fileUtil.upload(designInterior.getId(),file)));

        repository.save(designInterior);
        return designInterior;
    }

    @Override
    public DesignInterior getDesignInterior(String id) {
        if(repository.findById(id).isPresent()) {
            DesignInterior designInterior = repository.findById(id).get();
            return designInterior;
        }else throw new FileNotFoundException(id);
    }

    @Override
    public List<DesignInterior> getAllDesignInterior() {
        return repository.findAll();
    }

    @Override
    public void deleteDesignInterior(String id) {
        repository.deleteById(id);
    }
}
