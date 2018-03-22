package com.wright.generator;

import com.wright.model.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneratorFactory {

    @Autowired
    private SieveGenerator sieveGenerator;
    @Autowired
    private TrialDivisionGenerator trialDivisionGenerator;

    /**
     * @should return sieve generator if sieve algorithm passed in
     * @should return trial division generator if trial division algorithm passed in
     * @should default to sieve generator
     */
    public PrimeNumberGenerator generatorFor(Algorithm algorithm) {
        switch (algorithm) {
            case SIEVE:
                return sieveGenerator;
            case TRIAL_DIVISION:
                return trialDivisionGenerator;
            default:
                return sieveGenerator;
        }
    }
}