package com.wright.service;

import com.wright.dao.InMemoryPrimeNumberStorage;
import com.wright.generator.PrimeNumberGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class PrimeNumberGeneratorServiceTest {

    private PrimeNumberGeneratorService underTest;

    @MockBean
    private InMemoryPrimeNumberStorage mockStorage;
    @MockBean
    private PrimeNumberGenerator mockPrimeNumberGenerator;

    @Before
    public void setup() {
        underTest = new PrimeNumberGeneratorService();
    }

    /**
     * @verifies have lots of tests
     * @see PrimeNumberGeneratorService#calculatePrimesUpTo(Integer, String)
     */
    @Test
    public void calculatePrimesUpTo_should_have_lots_of_tests() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }
}