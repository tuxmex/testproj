package org.bedu.arg.testproj.exceptions;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(long id) {
        super("El integrante no existe", "ERR_MEM_NOT_FOUND", id);
    }
}
