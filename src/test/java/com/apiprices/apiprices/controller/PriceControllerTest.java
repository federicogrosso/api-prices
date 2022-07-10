package com.apiprices.apiprices.controller;

import com.apiprices.apiprices.model.Price;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @MethodSource("getPricesParams")
    public void testGetAppliedPrice(String params, Float priceExpected)
    {
        Float price =  this.restTemplate
                        .getForObject("http://localhost:" + port + "/prices/apply?" + params, Price.class).getPrice();
        assertEquals(priceExpected, price);
    }

    private static Object[] getPricesParams() {
        return new Object[]{
                new Object[]{"appliedDate=2020-06-14T10:00:00.000Z&productId=35455&brandId=1", 35.50f},
                new Object[]{"appliedDate=2020-06-14T16:00:00.000Z&productId=35455&brandId=1", 25.45f},
                new Object[]{"appliedDate=2020-06-14T21:00:00.000Z&productId=35455&brandId=1", 35.50f},
                new Object[]{"appliedDate=2020-06-15T10:00:00.000Z&productId=35455&brandId=1", 30.50f},
                new Object[]{"appliedDate=2020-06-16T21:00:00.000Z&productId=35455&brandId=1", 38.95f},
        };
    }
}
