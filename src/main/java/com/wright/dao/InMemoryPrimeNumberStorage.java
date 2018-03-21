package com.wright.dao;

import org.springframework.stereotype.Repository;

import java.util.SortedSet;
import java.util.TreeSet;

@Repository
public class InMemoryPrimeNumberStorage {

    private Integer largestValuePrimesCalculatedFor = 0;
    private TreeSet<Integer> calculatedPrimes = new TreeSet<>();

    public Integer getLargestValuePrimesCalculatedFor() {
        return largestValuePrimesCalculatedFor;
    }

    /**
     * @should get saved primes up to and including upper boundary
     * @should return empty set if no primes calculated before and zero upper boundary
     */
    public SortedSet<Integer> getPrimesUpTo(Integer upperBoundary) {
        return calculatedPrimes.subSet(0, false, upperBoundary, true);
    }

    /**
     * @should update largest value primes calculated for
     * @should update calculated primes
     */
    public void appendAdditionalPrimes(Integer newLargestValuePrimesCalculatedFor, SortedSet<Integer> additionalPrimes) {
        largestValuePrimesCalculatedFor = newLargestValuePrimesCalculatedFor;
        calculatedPrimes.addAll(additionalPrimes);
    }
}
