package com.wright;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrimeNumberGeneratorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCalculatePrimeNumberUpToValueAndReturnJson() throws Exception {
        mockMvc.perform(get("/primes/{initial}", 10)
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{\"Initial\":10, \"Primes\": [2,3,5,7]}"));
    }

    @Test
    public void shouldCalculatePrimeNumberUpToValueAndReturnXML() throws Exception {
        mockMvc.perform(get("/primes/{initial}", 10)
                .accept(MediaType.APPLICATION_XML_VALUE))
            .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML_VALUE))
                .andExpect(content().xml("<Response><Initial>10</Initial><Primes><Prime>2</Prime><Prime>3</Prime><Prime>5</Prime><Prime>7</Prime></Primes></Response>"));
    }

    @Test
    public void shouldCalculatePrimeNumbersUsingSieveAlgorithmArgument() throws Exception {
        mockMvc.perform(get("/primes/{initial}", 10)
                .param("algorithm", "Sieve")
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{\"Initial\":10, \"Primes\": [2,3,5,7]}"));
    }

    @Test
    public void shouldCalculatePrimeNumbersUsingTrialDivisionAlgorithmArgument() throws Exception {
        mockMvc.perform(get("/primes/{initial}", 10)
                .param("algorithm", "TrialDivision")
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{\"Initial\":10, \"Primes\": [2,3,5,7]}"));
    }
}
