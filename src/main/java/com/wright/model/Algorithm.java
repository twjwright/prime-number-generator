package com.wright.model;

public enum Algorithm {
    TRIAL_DIVISION,
    SIEVE,
    DEFAULT;

    /**
     * @should return sieve algorithm
     * @should return trial division algorithm
     * @should return default if algorithm not recognised
     */
    public static Algorithm get(String algorithm) {
        if ("sieve".equalsIgnoreCase(algorithm)) {
            return SIEVE;
        }
        if ("trialdivision".equalsIgnoreCase(algorithm)) {
            return TRIAL_DIVISION;
        }
        return DEFAULT;
    }
}
