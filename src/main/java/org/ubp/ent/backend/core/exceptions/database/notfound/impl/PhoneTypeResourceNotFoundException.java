package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 15/01/2016.
 */
public class PhoneTypeResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = -6769851852304412113L;

    public PhoneTypeResourceNotFoundException() {
    }

    public PhoneTypeResourceNotFoundException(String message) {
        super(message);
    }

    public PhoneTypeResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneTypeResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public PhoneTypeResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
