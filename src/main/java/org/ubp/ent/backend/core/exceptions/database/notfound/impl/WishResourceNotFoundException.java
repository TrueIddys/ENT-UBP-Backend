package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 23/02/2016.
 */
public class WishResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = -7119954936851147475L;

    public WishResourceNotFoundException() {
    }

    public WishResourceNotFoundException(String message) {
        super(message);
    }

    public WishResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WishResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public WishResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
