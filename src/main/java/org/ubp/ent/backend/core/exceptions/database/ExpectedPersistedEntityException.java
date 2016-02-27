package org.ubp.ent.backend.core.exceptions.database;

/**
 * Created by Anthony on 26/02/2016.
 */
public class ExpectedPersistedEntityException extends RuntimeException {
    private static final long serialVersionUID = -7907686552322651709L;

    public ExpectedPersistedEntityException() {
    }

    public ExpectedPersistedEntityException(String message) {
        super(message);
    }

    public ExpectedPersistedEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpectedPersistedEntityException(Throwable cause) {
        super(cause);
    }

    public ExpectedPersistedEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
