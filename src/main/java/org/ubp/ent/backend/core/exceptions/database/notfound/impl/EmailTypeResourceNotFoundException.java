package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 15/01/2016.
 */
public class EmailTypeResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = 2984791394712172739L;

    public EmailTypeResourceNotFoundException() {
    }

    public EmailTypeResourceNotFoundException(String message) {
        super(message);
    }

    public EmailTypeResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailTypeResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public EmailTypeResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
