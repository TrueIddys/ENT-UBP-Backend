package org.ubp.ent.backend.core.model.wish;

/**
 * Created by Anthony on 17/02/2016.
 */
public enum WishState {
    ACCEPTED(Constants.ACCEPTED_VALUE),
    PENDING(Constants.PENDING_VALUE),
    DENIED(Constants.DENIED_VALUE);

    WishState(String genderString) {
    }

    public static class Constants {
        public static final String ACCEPTED_VALUE = "ACCEPTED";
        public static final String PENDING_VALUE = "PENDING";
        public static final String DENIED_VALUE = "DENIED";
    }
}
