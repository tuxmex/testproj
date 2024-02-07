package org.bedu.arg.testproj.exceptions;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuntimeException extends Exception {
    private final String code;
    private final Serializable details;

    public RuntimeException(String message, String code, Serializable details) {
        super(message);
        this.code = code;
        this.details = details;
    }
}