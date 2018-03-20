package com.wright.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Value;

import java.util.List;

@Value
public class Response {
    @JsonProperty("Initial")
    Integer initial;
    @JsonProperty(value="Primes")
    @JacksonXmlElementWrapper(localName = "Primes")
    @JacksonXmlProperty(localName = "Prime")
    List<Integer> primes;
}
