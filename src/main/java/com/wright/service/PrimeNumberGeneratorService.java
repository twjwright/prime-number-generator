package com.wright.service;

import com.wright.dao.InMemoryPrimeNumberStorage;
import com.wright.generator.GeneratorFactory;
import com.wright.model.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class PrimeNumberGeneratorService {

    @Autowired
    private InMemoryPrimeNumberStorage primeNumberStorage;
    @Autowired
    private GeneratorFactory generatorFactory;

    /**
     * @should have lots of tests
     */
    public List<Integer> calculatePrimesUpTo(Integer upperBoundary, String algorithm) {
        List<Integer> primes = new LinkedList<>();

        primes.addAll(primeNumberStorage.getPrimesUpTo(upperBoundary));

        Integer largestValuePrimesCalculatedFor = primeNumberStorage.getLargestValuePrimesCalculatedFor();

        if (upperBoundary > largestValuePrimesCalculatedFor) {
            Set<Integer> deltaPrimes = generatorFactory.generatorFor(Algorithm.get(algorithm)).calculatePrimesBetween(largestValuePrimesCalculatedFor, upperBoundary);
            primes.addAll(deltaPrimes);
            primeNumberStorage.appendAdditionalPrimes(upperBoundary, deltaPrimes);
        }
        return primes;
    }
}