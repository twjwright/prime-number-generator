package com.wright.model;

public enum Algorithm {
    TRIAL_DIVISION,
    SIEVE;

    public static Algorithm get(String algorithm) {
        if ("sieve".equalsIgnoreCase(algorithm)) {
            return SIEVE;
        } else if ("trialdivision".equalsIgnoreCase(algorithm)) {
            return TRIAL_DIVISION;
        } else {
            return SIEVE;
        }
    }
}
