package com.wright.controller;

import com.google.common.collect.Sets;
import com.wright.service.PrimeNumberGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PrimeNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrimeNumberGeneratorService mockService;

    /**
     * @verifies call calculator service with initial value passed in
     * @see PrimeNumberController#calculatePrimes(Integer, String)
     */
    @Test
    public void calculatePrimes_should_call_calculator_service_with_initial_value_passed_in() throws Exception {
        // given
        int initial = 2;

        // when
        ResultActions result = mockMvc.perform(get("/primes/{initial}", initial));

        // then
        verify(mockService).calculatePrimesUpTo(initial, null);
        result.andExpect(status().isOk());
    }

    /**
     * @verifies call calculator service with initial value and algorithm passed in
     * @see PrimeNumberController#calculatePrimes(Integer, String)
     */
    @Test
    public void calculatePrimes_should_call_calculator_service_with_initial_value_and_algorithm_passed_in() throws Exception {
        // given
        int initial = 2;
        String algorithm = "trialDivision";

        // when
        ResultActions result = mockMvc.perform(get("/primes/{initial}", initial).param("algorithm", algorithm));

        // then
        verify(mockService).calculatePrimesUpTo(initial, algorithm);
        result.andExpect(status().isOk());
    }

    /**
     * @verifies return json when accept json mediatype header is specified
     * @see PrimeNumberController#calculatePrimes(Integer, String)
     */
    @Test
    public void calculatePrimes_should_return_json_when_accept_json_mediatype_header_is_specified() throws Exception {
        // given
        int initial = 3;
        given(mockService.calculatePrimesUpTo(initial,null)).willReturn(Sets.newTreeSet(asList(2,3)));

        // when
        ResultActions result= mockMvc.perform(get("/primes/{initial}", initial).accept(APPLICATION_JSON_VALUE));

        // then
        result.andExpect(status().isOk()).
                andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_VALUE)).
                andExpect(content().json("{\"Initial\": 3, \"Primes\": [2,3]}"));
    }

    /**
     * @verifies return XML when accept XML mediatype header is specified
     * @see PrimeNumberController#calculatePrimes(Integer, String)
     */
    @Test
    public void calculatePrimes_should_return_XML_when_accept_XML_mediatype_header_is_specified() throws Exception {
        // given
        int initial = 3;
        given(mockService.calculatePrimesUpTo(initial, null)).willReturn(Sets.newTreeSet(asList(2,3)));

        // when
        ResultActions result= mockMvc.perform(get("/primes/{initial}", initial).accept(APPLICATION_XML_VALUE));

        // then
        result.andExpect(status().isOk()).
                andExpect(content().contentTypeCompatibleWith(APPLICATION_XML_VALUE)).
                andExpect(content().xml("<Response><Initial>3</Initial><Primes><Prime>2</Prime><Prime>3</Prime></Primes></Response>"));
    }

    /**
     * @verifies return bad request if non-integer initial value passed in
     * @see PrimeNumberController#calculatePrimes(Integer, String)
     */
    @Test
    public void calculatePrimes_should_return_bad_request_if_noninteger_initial_value_passed_in() throws Exception {
        // given
        double initial = 0.2;

        // when
        ResultActions result = mockMvc.perform(get("/primes/{initial}", initial));

        // then
        verifyZeroInteractions(mockService);
        result.andExpect(status().isBadRequest());
    }

    /**
     * @verifies return bad request if non-numberical initial value passed in
     * @see PrimeNumberController#calculatePrimes(Integer, String)
     */
    @Test
    public void calculatePrimes_should_return_bad_request_if_nonnumberical_initial_value_passed_in() throws Exception {
        // given
        String initial = "five";

        // when
        ResultActions result = mockMvc.perform(get("/primes/{initial}", initial));

        // then
        verifyZeroInteractions(mockService);
        result.andExpect(status().isBadRequest());
    }
}