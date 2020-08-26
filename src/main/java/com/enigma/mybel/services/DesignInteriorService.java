package com.enigma.mybel.services;

import com.enigma.mybel.entity.DesignInterior;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DesignInteriorService {
    DesignInterior saveDesignInterior(MultipartFile file, String requestBody) throws IOException;
    DesignInterior saveFormDesign(DesignInterior interior);
    DesignInterior updateDesignInterior(MultipartFile file,String requestBody) throws IOException;
    DesignInterior getDesignInterior(String id);
    List<DesignInterior> getAllDesignInterior();
    void deleteDesignInterior(String id);
}
