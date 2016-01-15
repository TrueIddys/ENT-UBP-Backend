package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 15/01/2016.
 */
public class TeacherResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = 2770019572410225009L;

    public TeacherResourceNotFoundException() {
    }

    public TeacherResourceNotFoundException(String message) {
        super(message);
    }

    public TeacherResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeacherResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public TeacherResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
