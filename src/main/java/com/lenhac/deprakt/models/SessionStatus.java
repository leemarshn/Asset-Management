package com.lenhac.deprakt.models;

public enum SessionStatus {
        ACTIVE("Active"),
        EXPIRED("Expired"),
        LOGGED_OUT("Logged Out");

        private final String value;

        SessionStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        // Optional: Add a method to get the enum from the string value
        public static SessionStatus fromValue(String value) {
            for (SessionStatus status : SessionStatus.values()) {
                if (status.value.equalsIgnoreCase(value)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Invalid SessionStatus value: " + value);
        }
    }

}
