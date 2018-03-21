package com.wright.service;

import com.wright.dao.InMemoryPrimeNumberStorage;
import com.wright.generator.GeneratorFactory;
import com.wright.model.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class PrimeNumberGeneratorService {

    @Autowired
    private InMemoryPrimeNumberStorage primeNumberStorage;
    @Autowired
    private GeneratorFactory generatorFactory;

    /**
     * @should return empty set if negative upper boundary provided
     * @should retrieve already calculated prime numbers from storage
     * @should not call generator if already calculated primes for a value greater than upper boundary supplied
     * @should use passed in generator if already calculated primes for a value less than upper boundary supplied
     * @should append additionally calculated primes to storage
     */
    public SortedSet<Integer> calculatePrimesUpTo(Integer upperBoundary, String algorithm) {
        SortedSet<Integer> primes = new TreeSet<>();

        if (upperBoundary < 0) {
            return primes;
        }

        primes.addAll(primeNumberStorage.getPrimesUpTo(upperBoundary));

        Integer largestValuePrimesCalculatedFor = primeNumberStorage.getLargestValuePrimesCalculatedFor();

        if (upperBoundary > largestValuePrimesCalculatedFor) {
            SortedSet<Integer> deltaPrimes = generatorFactory.generatorFor(Algorithm.get(algorithm)).calculatePrimesBetween(largestValuePrimesCalculatedFor, upperBoundary);
            primes.addAll(deltaPrimes);
            primeNumberStorage.appendAdditionalPrimes(upperBoundary, deltaPrimes);
        }
        return primes;
    }
}