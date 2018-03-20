package com.wright.generator;

import java.util.Set;

public interface PrimeNumberGenerator {

    Set<Integer> calculatePrimesBetween(Integer lowerBoundary, Integer upperBoundary);

}