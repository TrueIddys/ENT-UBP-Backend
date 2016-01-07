package org.ubp.ent.backend.core.exceptions.database.notfound;

/**
 * Created by Anthony on 07/01/2016.
 */
public abstract class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 6417131835607836467L;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
