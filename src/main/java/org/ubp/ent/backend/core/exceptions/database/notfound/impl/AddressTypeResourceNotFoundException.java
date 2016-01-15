package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 15/01/2016.
 */
public class AddressTypeResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = -264798313663989638L;

    public AddressTypeResourceNotFoundException() {
    }

    public AddressTypeResourceNotFoundException(String message) {
        super(message);
    }

    public AddressTypeResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressTypeResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public AddressTypeResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
