package com.enigma.mybel.services;

import com.enigma.mybel.constants.Constant;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUtilImpl implements FileUtil {
    private final Path storageLocation= Paths.get("uploads").toAbsolutePath().normalize();

    @Override
        public Resource read(String fileName) {
        String exceptionMessage= String.format(Constant.FILENOTFOUND);
        try {
            Path file = storageLocation.resolve(String.format(Constant.FOLDERPATH,fileName));
            Resource resource= new UrlResource(file.toUri());
            if(!resource.exists()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,exceptionMessage);
            return resource;
        }catch (MalformedURLException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exceptionMessage);
        }
    }

    @Override
    public String upload(String id, MultipartFile file) throws IOException {
        File upload=new File(String.format(Constant.FOLDERPATH,id));
        file.transferTo(upload);
        return id;
    }
}
