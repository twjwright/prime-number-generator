package com.wright.generator;

import org.springframework.stereotype.Component;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

import static com.google.common.collect.Sets.newTreeSet;
import static java.util.stream.Collectors.toCollection;

/**
 * Prime number generator using the Trial Division method
 */
@Component
public class TrialDivisionGenerator implements PrimeNumberGenerator {

    /**
     * @should return empty set if lower boundary less than upper boundary
     * @should return empty set if upper boundary less than two
     * @should return empty set if lower and upper boundaries are both one
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
            return newTreeSet();
        }

        return IntStream.rangeClosed(lowerBoundary, upperBoundary).filter(this::isPrime).boxed().collect(toCollection(TreeSet::new));
    }

    private boolean isPrime(Integer candidatePrime) {
        if (candidatePrime == 2) {
            return true;
        }
        if (candidatePrime < 2) {
            return false;
        }
        if (candidatePrime % 2 == 0) {
            return false;
        }
        for (int primeFactor = 3; primeFactor <= Math.sqrt(candidatePrime); primeFactor += 2) {
            if (candidatePrime % primeFactor == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean invalidInput(Integer lowerBoundary, Integer upperBoundary) {
        return lowerBoundary == null || upperBoundary == null;
    }
}
