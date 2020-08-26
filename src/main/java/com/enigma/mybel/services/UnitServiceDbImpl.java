package com.enigma.mybel.services;

import com.enigma.mybel.constants.Constant;
import com.enigma.mybel.entity.Type;
import com.enigma.mybel.entity.Unit;
import com.enigma.mybel.entity.Vendor;
import com.enigma.mybel.exceptions.FileNotFoundException;
import com.enigma.mybel.repositories.UnitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UnitServiceDbImpl implements UnitService{
    @Autowired
    UnitRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    VendorService vendorService;

    @Autowired
    TypeService typeService;

    @Override
    public Unit saveUnit(MultipartFile file, String requestBody) throws IOException {
        Unit unit = repository.save(objectMapper.readValue(requestBody,Unit.class));
        Vendor vendor = vendorService.getVendor(unit.getIdVendor());
        Type type = typeService.getType(unit.getIdType());

        if(file!=null) unit.setPicture(String.format(Constant.PATHUNIT,fileUtil.upload(unit.getId(),file)));

        unit.setVendor(vendor);
        unit.setType(type);

        repository.save(unit);
        return unit;
    }

    @Override
    public Unit saveFormUnit(Unit unit) {
        return repository.save(unit);
    }

    @Override
    public Unit updateUnit(MultipartFile file,String requestBody) throws IOException {
        Unit unit = repository.save(objectMapper.readValue(requestBody,Unit.class));
        if(file!=null) unit.setPicture(String.format(Constant.PATHUNIT,fileUtil.upload(unit.getId(),file)));
        return repository.save(unit);
    }

    @Override
    public Unit getUnit(String id) {
        if(repository.findById(id).isPresent()) {
            Unit unit = repository.findById(id).get();
            return unit;
        }else throw new FileNotFoundException(id);

    }

    @Override
    public Page<Unit> getAllUnit(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deleteUnit(String id) {
        repository.deleteById(id);
    }
}
