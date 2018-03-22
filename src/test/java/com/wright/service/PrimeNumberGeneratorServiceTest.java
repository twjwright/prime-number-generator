package com.wright.service;

import com.google.common.collect.Sets;
import com.wright.dao.InMemoryPrimeNumberStorage;
import com.wright.generator.GeneratorFactory;
import com.wright.generator.PrimeNumberGenerator;
import com.wright.model.Algorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.SortedSet;

import static com.google.common.collect.Sets.newTreeSet;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(SpringRunner.class)
public class PrimeNumberGeneratorServiceTest {

    @InjectMocks
    private PrimeNumberGeneratorService underTest;

    @Mock
    private InMemoryPrimeNumberStorage mockStorage;
    @Mock
    private GeneratorFactory mockFactory;
    @Mock
    private PrimeNumberGenerator mockPrimeNumberGenerator;

    @Before
    public void setup() {
        given(mockFactory.generatorFor(Algorithm.SIEVE)).willReturn(mockPrimeNumberGenerator);
    }

    /**
     * @verifies return empty set if negative upper boundary provided
     * @see PrimeNumberGeneratorService#calculatePrimesUpTo(Integer, String)
     */
    @Test
    public void calculatePrimesUpTo_should_return_empty_set_if_negative_upper_boundary_provided() throws Exception {
        // given
        Integer upperBoundary = -1;
        String algorithm = "sieve";

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesUpTo(upperBoundary, algorithm);

        // then
        assertTrue(calculatedPrimes.isEmpty());
    }

    /**
     * @verifies retrieve already calculated prime numbers from storage
     * @see PrimeNumberGeneratorService#calculatePrimesUpTo(Integer, String)
     */
    @Test
    public void calculatePrimesUpTo_should_retrieve_already_calculated_prime_numbers_from_storage() throws Exception {
        // given
        Integer upperBoundary = 10;
        String algorithm = "sieve";
        SortedSet<Integer> alreadyCalculatedPrimes = newTreeSet(asList(2, 3, 5, 7));
        given(mockStorage.getLargestValuePrimesCalculatedFor()).willReturn(10);
        given(mockStorage.getPrimesUpTo(upperBoundary)).willReturn(alreadyCalculatedPrimes);

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesUpTo(upperBoundary, algorithm);

        // then
        verify(mockStorage).getPrimesUpTo(upperBoundary);
        assertEquals(alreadyCalculatedPrimes, calculatedPrimes);
    }

    /**
     * @verifies not call generator if already calculated primes for a value greater than upper boundary supplied
     * @see PrimeNumberGeneratorService#calculatePrimesUpTo(Integer, String)
     */
    @Test
    public void calculatePrimesUpTo_should_not_call_generator_if_already_calculated_primes_for_a_value_greater_than_upper_boundary_supplied() throws Exception {
        // given
        Integer upperBoundary = 8;
        String algorithm = "sieve";
        given(mockStorage.getLargestValuePrimesCalculatedFor()).willReturn(10);

        // when
        underTest.calculatePrimesUpTo(upperBoundary, algorithm);

        // then
        verifyZeroInteractions(mockFactory, mockPrimeNumberGenerator);
    }

    /**
     * @verifies use passed in generator if already calculated primes for a value less than upper boundary supplied
     * @see PrimeNumberGeneratorService#calculatePrimesUpTo(Integer, String)
     */
    @Test
    public void calculatePrimesUpTo_should_use_passed_in_generator_if_already_calculated_primes_for_a_value_less_than_upper_boundary_supplied() throws Exception {
        // given
        Integer upperBoundary = 10;
        String algorithm = "sieve";
        SortedSet<Integer> alreadyCalculatedPrimes = newTreeSet(asList(2, 3, 5));
        SortedSet<Integer> generatedPrimes = newTreeSet(singletonList(7));
        given(mockStorage.getLargestValuePrimesCalculatedFor()).willReturn(6);
        given(mockStorage.getPrimesUpTo(upperBoundary)).willReturn(alreadyCalculatedPrimes);
        given(mockPrimeNumberGenerator.calculatePrimesBetween(6, 10)).willReturn(generatedPrimes);

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesUpTo(upperBoundary, algorithm);

        // then
        verify(mockFactory).generatorFor(Algorithm.SIEVE);
        verify(mockPrimeNumberGenerator).calculatePrimesBetween(6, 10);
        assertEquals(Sets.union(alreadyCalculatedPrimes, generatedPrimes), calculatedPrimes);
    }

    /**
     * @verifies append additionally calculated primes to storage
     * @see PrimeNumberGeneratorService#calculatePrimesUpTo(Integer, String)
     */
    @Test
    public void calculatePrimesUpTo_should_append_additionally_calculated_primes_to_storage() throws Exception {
        Integer upperBoundary = 10;
        String algorithm = "sieve";
        SortedSet<Integer> generatedPrimes = newTreeSet(asList(2, 3, 5, 7));
        given(mockStorage.getLargestValuePrimesCalculatedFor()).willReturn(0);
        given(mockStorage.getPrimesUpTo(upperBoundary)).willReturn(newTreeSet());
        given(mockPrimeNumberGenerator.calculatePrimesBetween(0, 10)).willReturn(generatedPrimes);

        // when
        Set<Integer> calculatedPrimes = underTest.calculatePrimesUpTo(upperBoundary, algorithm);

        // then
        verify(mockStorage).appendAdditionalPrimes(10, generatedPrimes);
        assertEquals(generatedPrimes, calculatedPrimes);
    }
}