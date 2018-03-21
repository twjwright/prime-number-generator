package com.wright.controller;

import com.wright.model.Response;
import com.wright.service.PrimeNumberGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PrimeNumberController {

    @Autowired
    private PrimeNumberGeneratorService primeNumberGeneratorService;

    /**
     * @should call calculator service with initial value passed in
     * @should call calculator service with initial value and algorithm passed in
     * @should return json when accept json mediatype header is specified
     * @should return XML when accept XML mediatype header is specified
     * @should return bad request if non-integer initial value passed in
     * @should return bad request if non-numberical initial value passed in
     */
    @RequestMapping(value = "/primes/{initial}", method = RequestMethod.GET)
    public ResponseEntity<Response> calculatePrimes(@PathVariable(value = "initial") Integer initial,
                                                    @RequestParam(name = "algorithm", required = false) String algorithm) {
        return ResponseEntity.ok(new Response(initial, primeNumberGeneratorService.calculatePrimesUpTo(initial, algorithm)));
    }
}