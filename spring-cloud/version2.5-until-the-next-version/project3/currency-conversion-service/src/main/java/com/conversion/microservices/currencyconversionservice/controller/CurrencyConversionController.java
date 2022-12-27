package com.conversion.microservices.currencyconversionservice.controller;

import com.conversion.microservices.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    // http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
            ){

        // You can find that microservice in https://github.com/gil-son/spring-ecosystem/tree/main/spring-cloud/version2.5-until-the-next-version/project2/currency-exchange-service
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVariables);

        CurrencyConversion bodyCurrencyConversion = responseEntity.getBody();

        return new CurrencyConversion(bodyCurrencyConversion.getId(),
                bodyCurrencyConversion.getFrom(),
                bodyCurrencyConversion.getTo(),
                bodyCurrencyConversion.getQuantity(),
                bodyCurrencyConversion.getConversionMultiple(),
                quantity.multiply(bodyCurrencyConversion.getConversionMultiple()),
                bodyCurrencyConversion.getEnvironment());
    };

}
