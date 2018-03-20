package com.wright.generator;

import com.wright.model.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneratorFactory {

    @Autowired
    private SievePrimeNumberGenerator sievePrimeNumberGenerator;
    @Autowired
    private TrialDivisionPrimeNumberGenerator trialDivisionPrimeNumberGenerator;

    public PrimeNumberGenerator generatorFor(Algorithm algorithm) {
        switch (algorithm) {
            case SIEVE:
                return sievePrimeNumberGenerator;
            case TRIAL_DIVISION:
                return trialDivisionPrimeNumberGenerator;
            default:
                return sievePrimeNumberGenerator;
        }
    }
}