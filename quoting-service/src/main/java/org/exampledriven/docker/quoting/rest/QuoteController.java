package org.exampledriven.docker.quoting.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.exampledriven.docker.quoting.model.Quote;

@RestController
public class QuoteController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.location}")
    private String serviceLocation;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public Quote doQuote() {
      Quote quote = restTemplate.getForObject(serviceLocation, Quote.class);
      quote.getValue().setQuote( quote.getValue().getQuote() + " - attaching some value.");
        return quote;
    }
}
