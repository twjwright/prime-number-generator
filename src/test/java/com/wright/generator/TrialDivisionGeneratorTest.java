package com.wright.generator;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrialDivisionGeneratorTest {

    private TrialDivisionGenerator underTest;

    @Before
    public void setup() {
        underTest = new TrialDivisionGenerator();
    }

    /**
     * @verifies return empty set if lower boundary less than upper boundary
     * @see TrialDivisionGenerator#calculatePrimesBetween(Integer, Integer)
     */
    @Test
    public void calculatePrimesBetween_should_return_empty_set_if_lower_boundary_less_than_upper_boundary() throws Exception {
        // given
        Integer lowerBoundary = 10;
        Integer upperBoundary = 1;

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesBetween(lowerBoundary, upperBoundary);

        // then
        assertTrue(calculatedPrimes.isEmpty());
    }

    /**
     * @verifies return empty set if upper boundary less than two
     * @see TrialDivisionGenerator#calculatePrimesBetween(Integer, Integer)
     */
    @Test
    public void calculatePrimesBetween_should_return_empty_set_if_upper_boundary_less_than_two() throws Exception {
        // given
        Integer lowerBoundary = 0;
        Integer upperBoundary = 1;

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesBetween(lowerBoundary, upperBoundary);

        // then
        assertTrue(calculatedPrimes.isEmpty());
    }

    /**
     * @verifies return empty set if lower and upper boundaries are both one
     * @see TrialDivisionGenerator#calculatePrimesBetween(Integer, Integer)
     */
    @Test
    public void calculatePrimesBetween_should_return_empty_set_if_lower_and_upper_boundaries_are_both_one() throws Exception {
        // given
        Integer lowerBoundary = 1;
        Integer upperBoundary = 1;

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesBetween(lowerBoundary, upperBoundary);

        // then
        assertTrue(calculatedPrimes.isEmpty());
    }

    /**
     * @verifies return empty set if null lower boundary passed in
     * @see TrialDivisionGenerator#calculatePrimesBetween(Integer, Integer)
     */
    @Test
    public void calculatePrimesBetween_should_return_empty_set_if_null_lower_boundary_passed_in() throws Exception {
        // given
        Integer lowerBoundary = null;
        Integer upperBoundary = 1;

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesBetween(lowerBoundary, upperBoundary);

        // then
        assertTrue(calculatedPrimes.isEmpty());
    }

    /**
     * @verifies return empty set if null upper boundary passed in
     * @see TrialDivisionGenerator#calculatePrimesBetween(Integer, Integer)
     */
    @Test
    public void calculatePrimesBetween_should_return_empty_set_if_null_upper_boundary_passed_in() throws Exception {
        // given
        Integer lowerBoundary = 10;
        Integer upperBoundary = null;

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesBetween(lowerBoundary, upperBoundary);

        // then
        assertTrue(calculatedPrimes.isEmpty());
    }

    /**
     * @verifies return prime numbers from zero lower boundary to upper boundary inclusive
     * @see TrialDivisionGenerator#calculatePrimesBetween(Integer, Integer)
     */
    @Test
    public void calculatePrimesBetween_should_return_prime_numbers_from_zero_lower_boundary_to_upper_boundary_inclusive() throws Exception {
        // given
        Integer lowerBoundary = 0;
        Integer upperBoundary = 7;

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesBetween(lowerBoundary, upperBoundary);

        // then
        assertEquals(newHashSet(2, 3, 5, 7), calculatedPrimes);
    }

    /**
     * @verifies return prime numbers from zero lower boundary to upper boundary exclusive
     * @see TrialDivisionGenerator#calculatePrimesBetween(Integer, Integer)
     */
    @Test
    public void calculatePrimesBetween_should_return_prime_numbers_from_zero_lower_boundary_to_upper_boundary_exclusive() throws Exception {
        // given
        Integer lowerBoundary = 0;
        Integer upperBoundary = 10;

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesBetween(lowerBoundary, upperBoundary);

        // then
        assertEquals(newHashSet(2, 3, 5, 7), calculatedPrimes);
    }

    /**
     * @verifies return prime numbers from non-zero lower boundary and upper boundary inclusive
     * @see TrialDivisionGenerator#calculatePrimesBetween(Integer, Integer)
     */
    @Test
    public void calculatePrimesBetween_should_return_prime_numbers_from_nonzero_lower_boundary_and_upper_boundary_inclusive() throws Exception {
        // given
        Integer lowerBoundary = 23;
        Integer upperBoundary = 37;

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesBetween(lowerBoundary, upperBoundary);

        // then
        assertEquals(newHashSet(23, 29, 31, 37), calculatedPrimes);
    }

    /**
     * @verifies return prime numbers from non-zero lower boundary and upper boundary exclusive
     * @see TrialDivisionGenerator#calculatePrimesBetween(Integer, Integer)
     */
    @Test
    public void calculatePrimesBetween_should_return_prime_numbers_from_nonzero_lower_boundary_and_upper_boundary_exclusive() throws Exception {
        // given
        Integer lowerBoundary = 20;
        Integer upperBoundary = 40;

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesBetween(lowerBoundary, upperBoundary);

        // then
        assertEquals(newHashSet(23, 29, 31, 37), calculatedPrimes);
    }

    /**
     * @verifies return prime numbers from zero lower boundary to large upper boundary
     * @see TrialDivisionGenerator#calculatePrimesBetween(Integer, Integer)
     */
    @Test
    public void calculatePrimesBetween_should_return_prime_numbers_from_zero_lower_boundary_to_large_upper_boundary() throws Exception {
        // given
        Integer lowerBoundary = 0;
        Integer upperBoundary = 550;

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesBetween(lowerBoundary, upperBoundary);

        // then
        Set<Integer> expectedPrimes = newHashSet(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547);
        assertEquals(expectedPrimes, calculatedPrimes);
    }
}