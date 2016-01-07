package org.ubp.ent.backend.core.exceptions.database;

/**
 * Created by Anthony on 07/01/2016.
 */
public class ModelConstraintViolationException extends RuntimeException {
    private static final long serialVersionUID = -398379325925193384L;

    public ModelConstraintViolationException() {
    }

    public ModelConstraintViolationException(String message) {
        super(message);
    }

    public ModelConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelConstraintViolationException(Throwable cause) {
        super(cause);
    }

    public ModelConstraintViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
