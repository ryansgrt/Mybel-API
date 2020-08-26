package com.enigma.mybel.exceptions;

import com.enigma.mybel.constants.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String id) {
        super(String.format(Constant.RESOURCENOTFOUND,id));
    }
}
