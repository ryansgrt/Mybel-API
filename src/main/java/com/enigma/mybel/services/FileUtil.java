package com.enigma.mybel.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUtil {
    Resource read(String fileName);
    String upload(String id, MultipartFile file) throws IOException;
}
