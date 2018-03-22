package com.wright.generator;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Prime number generator using the Sieve method
 */
@Component
public class SieveGenerator implements PrimeNumberGenerator {

    /**
     * @should return empty set if lower boundary less than upper boundary
     * @should return empty set if null lower boundary passed in
     * @should return empty set if null upper boundary passed in
     * @should return prime numbers from zero lower boundary to upper boundary inclusive
     * @should return prime numbers from zero lower boundary to upper boundary exclusive
     * @should return prime numbers from non-zero lower boundary and upper boundary inclusive
     * @should return prime numbers from non-zero lower boundary and upper boundary exclusive
     * @should return prime numbers from zero lower boundary to large upper boundary
     */
    @Override
    public SortedSet<Integer> calculatePrimesBetween(Integer lowerBoundary, Integer upperBoundary) {
        if (invalidInput(lowerBoundary, upperBoundary)) {
            return Sets.newTreeSet();
        }

        TreeSet<Integer> primeNumbers = extractPrimesFromNumberMap(generatePrimeNumberMapUpTo(upperBoundary), upperBoundary);

        return primeNumbers.subSet(lowerBoundary, true, upperBoundary, true);

    }

    private boolean invalidInput(Integer lowerBoundary, Integer upperBoundary) {
        return lowerBoundary == null || upperBoundary == null || lowerBoundary > upperBoundary;
    }

    private TreeSet<Integer> extractPrimesFromNumberMap(boolean[] primes, Integer upperBoundary) {
        TreeSet<Integer> primeNumbers = new TreeSet<>();
        for (int i = 2; i <= upperBoundary; i++) {
            if (primes[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    private boolean[] generatePrimeNumberMapUpTo(Integer upperBoundary) {
        boolean primes[] = new boolean[upperBoundary + 1];
        Arrays.fill(primes, true);

        for (int primeFactor = 2; primeFactor <= Math.sqrt(upperBoundary); primeFactor++) {
            if (primes[primeFactor]) {
                for (int multiplier = 2; primeFactor * multiplier <= upperBoundary; multiplier++) {
                    primes[primeFactor * multiplier] = false;
                }
            }
        }
        return primes;
    }
}
