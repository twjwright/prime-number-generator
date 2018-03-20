package com.wright.dao;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Repository
public class InMemoryPrimeNumberStorage {

    private Integer largestValuePrimesCalculatedFor;
    private TreeSet<Integer> calculatedPrimes = new TreeSet<>();

    public InMemoryPrimeNumberStorage() {
        largestValuePrimesCalculatedFor = 2;
        calculatedPrimes.add(2);
    }

    public Integer getLargestValuePrimesCalculatedFor() {
        return largestValuePrimesCalculatedFor;
    }

    /**
     * @should work
     */
    public Set<Integer> getPrimesUpTo(Integer upperBoundary) {
        return calculatedPrimes.subSet(0, false, upperBoundary, true);
    }

    public void appendAdditionalPrimes(Integer newLargestValuePrimesCalculatedFor, Set<Integer> additionalPrimes) {
        largestValuePrimesCalculatedFor = newLargestValuePrimesCalculatedFor;
        calculatedPrimes.addAll(additionalPrimes);
    }
}
