package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 29/11/2015.
 */
public class ClassroomResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = 4895920532385203593L;

    public ClassroomResourceNotFoundException() {
    }

    public ClassroomResourceNotFoundException(String message) {
        super(message);
    }

    public ClassroomResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassroomResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ClassroomResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
