package com.apiprices.apiprices.controller;

import com.apiprices.apiprices.exception.ApiException;
import com.apiprices.apiprices.dto.PriceRequestParams;
import com.apiprices.apiprices.dto.PricesAppliedResponse;
import com.apiprices.apiprices.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller in charge of requests where is involve the {@link com.apiprices.apiprices.model.Price} entity.
 */
@RestController
public class PriceController {

    @Autowired
    private PriceService service;

    /**
     * For a date, brand and product, return the correct price to apply.
     *
     * @param priceRequestParams application which invoke the endpoint
     * @return PricesAppliedResponse
     * @throws ApiException Wrong params o internal error
     */
    @GetMapping("/prices")
    public PricesAppliedResponse getPriceToApply(@Valid PriceRequestParams priceRequestParams) throws ApiException {
       return service.getPriceToApply(priceRequestParams);
    }
}