package com.wright.dao;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.SortedSet;

import static com.google.common.collect.Sets.newTreeSet;
import static com.google.common.primitives.Ints.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InMemoryPrimeNumberStorageTest {

    private InMemoryPrimeNumberStorage underTest;

    @Before
    public void setup() {
        underTest = new InMemoryPrimeNumberStorage();
    }

    /**
     * @verifies get saved primes up to and including upper boundary
     * @see InMemoryPrimeNumberStorage#getPrimesUpTo(Integer)
     */
    @Test
    public void getPrimesUpTo_should_get_saved_primes_up_to_and_including_upper_boundary() throws Exception {
        // given
        SortedSet<Integer> primesCalculatedSoFar = newTreeSet(asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
        underTest.appendAdditionalPrimes(30, primesCalculatedSoFar);

        // when
        Set<Integer> primesUpTo = underTest.getPrimesUpTo(29);

        // then
        assertEquals(primesCalculatedSoFar, primesUpTo);
    }

    /**
     * @verifies return empty set if no primes calculated before and zero upper boundary
     * @see InMemoryPrimeNumberStorage#getPrimesUpTo(Integer)
     */
    @Test
    public void getPrimesUpTo_should_return_empty_set_if_no_primes_calculated_before_and_zero_upper_boundary() throws Exception {
        // given

        // when
        Set<Integer> primesUpTo = underTest.getPrimesUpTo(0);

        // then
        assertTrue(primesUpTo.isEmpty());
    }

    /**
     * @verifies update largest value primes calculated for
     * @see InMemoryPrimeNumberStorage#appendAdditionalPrimes(Integer, SortedSet)
     */
    @Test
    public void appendAdditionalPrimes_should_update_largest_value_primes_calculated_for() throws Exception {
        // given
        Integer newLargestValuePrimesCalculatedFor = 4;
        underTest.appendAdditionalPrimes(newLargestValuePrimesCalculatedFor, newTreeSet(asList(2, 3)));

        // when
        Integer largestValuePrimesCalculatedFor = underTest.getLargestValuePrimesCalculatedFor();

        // then
        assertEquals(newLargestValuePrimesCalculatedFor, largestValuePrimesCalculatedFor);
    }

    /**
     * @verifies update calculated primes
     * @see InMemoryPrimeNumberStorage#appendAdditionalPrimes(Integer, SortedSet)
     */
    @Test
    public void appendAdditionalPrimes_should_update_calculated_primes() throws Exception {
        SortedSet<Integer> primesCalculatedSoFar = newTreeSet(asList(2, 3, 5, 7, 11));
        SortedSet<Integer> additionalPrimes = newTreeSet(asList(13, 17, 19, 23, 29));
        underTest.appendAdditionalPrimes(12, primesCalculatedSoFar);

        // when
        underTest.appendAdditionalPrimes(30, additionalPrimes);
        Set<Integer> primesUpTo = underTest.getPrimesUpTo(30);

        // then
        assertEquals(Sets.union(primesCalculatedSoFar, additionalPrimes), primesUpTo);
    }
}