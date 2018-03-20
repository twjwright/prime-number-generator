package com.wright.dao;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class InMemoryPrimeNumberStorageTest {

    /**
     * @verifies work
     * @see InMemoryPrimeNumberStorage#getPrimesUpTo(Integer)
     */
    @Test
    public void getPrimesUpTo_should_work() throws Exception {
        InMemoryPrimeNumberStorage storage = new InMemoryPrimeNumberStorage();
        storage.appendAdditionalPrimes(10, Sets.newHashSet(3,5,7));
        System.out.println(storage.getPrimesUpTo(11));
    }
}