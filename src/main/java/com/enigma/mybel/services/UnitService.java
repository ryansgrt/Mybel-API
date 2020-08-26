package com.enigma.mybel.services;

import com.enigma.mybel.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UnitService {
    Unit saveUnit(MultipartFile file, String requestBody) throws IOException;
    Unit saveFormUnit(Unit unit);
    Unit updateUnit(MultipartFile file,String requestBody) throws IOException;
    Unit getUnit(String id);
    Page<Unit> getAllUnit(Pageable pageable);
    void deleteUnit(String id);
}
