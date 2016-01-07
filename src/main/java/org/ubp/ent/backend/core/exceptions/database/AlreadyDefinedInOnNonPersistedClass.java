package org.ubp.ent.backend.core.exceptions.database;

/**
 * Created by Anthony on 07/01/2016.
 */
public class AlreadyDefinedInOnNonPersistedClass extends RuntimeException {
    private static final long serialVersionUID = 1391303011903028026L;

    public AlreadyDefinedInOnNonPersistedClass() {
    }

    public AlreadyDefinedInOnNonPersistedClass(String message) {
        super(message);
    }

    public AlreadyDefinedInOnNonPersistedClass(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyDefinedInOnNonPersistedClass(Throwable cause) {
        super(cause);
    }

    public AlreadyDefinedInOnNonPersistedClass(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
