package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 15/01/2016.
 */
public class EmailResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = -4844089219403877738L;

    public EmailResourceNotFoundException() {
    }

    public EmailResourceNotFoundException(String message) {
        super(message);
    }

    public EmailResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public EmailResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
