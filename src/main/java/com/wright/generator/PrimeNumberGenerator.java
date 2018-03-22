package com.wright.generator;

import java.util.SortedSet;

public interface PrimeNumberGenerator {

    SortedSet<Integer> calculatePrimesBetween(Integer lowerBoundary, Integer upperBoundary);

}