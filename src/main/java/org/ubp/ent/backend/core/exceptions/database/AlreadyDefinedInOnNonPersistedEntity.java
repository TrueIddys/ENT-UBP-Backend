package org.ubp.ent.backend.core.exceptions.database;

/**
 * Created by Anthony on 07/01/2016.
 */
public class AlreadyDefinedInOnNonPersistedEntity extends RuntimeException {
    private static final long serialVersionUID = 1391303011903028026L;

    public AlreadyDefinedInOnNonPersistedEntity() {
    }

    public AlreadyDefinedInOnNonPersistedEntity(String message) {
        super(message);
    }

    public AlreadyDefinedInOnNonPersistedEntity(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyDefinedInOnNonPersistedEntity(Throwable cause) {
        super(cause);
    }

    public AlreadyDefinedInOnNonPersistedEntity(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
