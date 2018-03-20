package com.wright.generator;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TrialDivisionPrimeNumberGenerator implements PrimeNumberGenerator {

    @Override
    public Set<Integer> calculatePrimesBetween(Integer lowerBoundary, Integer upperBoundary) {
        Set<Integer> primes = new HashSet<>();
        for (int candidatePrime = lowerBoundary; candidatePrime <= upperBoundary; candidatePrime++) {
            for (int primeFactor = 2; primeFactor <= Math.sqrt(upperBoundary); primeFactor++) {
                if (candidatePrime % primeFactor == 0) {
                    break;
                }
            }
            primes.add(candidatePrime);
        }
        return primes;
    }
}
