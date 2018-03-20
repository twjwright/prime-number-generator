package com.wright.generator;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;

@Component
public class SievePrimeNumberGenerator implements PrimeNumberGenerator {

    /**
     * @should return empty set when null integer passed in
     * @should return empty set when zero is passed in
     * @should return empty set when number one is passed in
     * @should return correct factors up to value passed in
     */
    @Override
    public Set<Integer> calculatePrimesBetween(Integer lowerBoundary, Integer upperBoundary) {
        boolean primes[] = new boolean[upperBoundary + 1];
        Arrays.fill(primes, true);

        for (int primeFactor = 2; primeFactor <= Math.sqrt(upperBoundary); primeFactor++) {
            if (primes[primeFactor]) {
                for (int multiplier = 2; primeFactor * multiplier < upperBoundary; multiplier++) {
                    primes[primeFactor * multiplier] = false;
                }
            }
        }
        SortedSet<Integer> primeNumbers = new TreeSet<>();
        for (int i = 2; i <= upperBoundary; i++) {
            if (primes[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers.subSet(lowerBoundary, upperBoundary);

    }
}
