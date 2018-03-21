package com.wright.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgorithmTest {

    /**
     * @verifies return sieve algorithm
     * @see Algorithm#get(String)
     */
    @Test
    public void get_should_return_sieve_algorithm() throws Exception {
        // given

        // when
        Algorithm algorithm = Algorithm.get("Sieve");

        // then
        assertEquals(Algorithm.SIEVE, algorithm);
    }

    /**
     * @verifies return trial division algorithm
     * @see Algorithm#get(String)
     */
    @Test
    public void get_should_return_trial_division_algorithm() throws Exception {
        // given

        // when
        Algorithm algorithm = Algorithm.get("TrialDivision");

        // then
        assertEquals(Algorithm.TRIAL_DIVISION, algorithm);
    }

    /**
     * @verifies return default if algorithm not recognised
     * @see Algorithm#get(String)
     */
    @Test
    public void get_should_return_default_if_algorithm_not_recognised() throws Exception {
        // given

        // when
        Algorithm algorithm = Algorithm.get("NotMatchingAlgorithm");

        // then
        assertEquals(Algorithm.DEFAULT, algorithm);
    }
}