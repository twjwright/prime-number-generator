package com.wright.generator;

import com.wright.model.Algorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class GeneratorFactoryTest {

    @InjectMocks
    private GeneratorFactory underTest;

    @Mock
    private SieveGenerator mockSieveGenerator;
    @Mock
    private TrialDivisionGenerator mockTrialDivisionGenerator;

    /**
     * @verifies return sieve generator if sieve algorithm passed in
     * @see GeneratorFactory#generatorFor(com.wright.model.Algorithm)
     */
    @Test
    public void generatorFor_should_return_sieve_generator_if_sieve_algorithm_passed_in() throws Exception {
        // given

        // when
        PrimeNumberGenerator primeNumberGenerator = underTest.generatorFor(Algorithm.SIEVE);

        // then
        assertEquals(mockSieveGenerator, primeNumberGenerator);
    }

    /**
     * @verifies return trial division generator if trial division algorithm passed in
     * @see GeneratorFactory#generatorFor(Algorithm)
     */
    @Test
    public void generatorFor_should_return_trial_division_generator_if_trial_division_algorithm_passed_in() throws Exception {
        // given

        // when
        PrimeNumberGenerator primeNumberGenerator = underTest.generatorFor(Algorithm.TRIAL_DIVISION);

        // then
        assertEquals(mockTrialDivisionGenerator, primeNumberGenerator);
    }

    /**
     * @verifies default to sieve generator
     * @see GeneratorFactory#generatorFor(Algorithm)
     */
    @Test
    public void generatorFor_should_default_to_sieve_generator() throws Exception {
        // given

        // when
        PrimeNumberGenerator primeNumberGenerator = underTest.generatorFor(Algorithm.DEFAULT);

        // then
        assertEquals(mockSieveGenerator, primeNumberGenerator);
    }
}