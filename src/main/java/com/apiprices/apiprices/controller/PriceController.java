package com.apiprices.apiprices.controller;

import com.apiprices.apiprices.exception.ApiException;
import com.apiprices.apiprices.request.PriceRequestParams;
import com.apiprices.apiprices.response.PricesAppliedResponse;
import com.apiprices.apiprices.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

/**
 * Controller in charge of requests where is involve the {@link com.apiprices.apiprices.model.Price} entity.
 */
@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService service;

    /**
     * For a site, return the carriers requested.
     *
     * @param priceRequestParams application which invoke the endpoint
     * @return PricesAppliedResponse
     * @throws ApiException Wrong params o internal error
     */
    @GetMapping("/apply")
    public PricesAppliedResponse getPriceToApply(@Valid PriceRequestParams priceRequestParams, HttpServletResponse res) throws ApiException {
        PricesAppliedResponse response = service.getPriceToApply(priceRequestParams);
        if (Objects.isNull(response.getPrice())) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return response;
    }
}