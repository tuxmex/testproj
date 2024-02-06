package org.bedu.arg.testproj.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(long id) {
        super("El proyecto no existe", "ERR_PRO_NOT_FOUND", id);
    }
}
