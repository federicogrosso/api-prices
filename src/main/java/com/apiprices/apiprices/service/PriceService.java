package com.apiprices.apiprices.service;

import com.apiprices.apiprices.exception.ApiException;
import com.apiprices.apiprices.model.Price;
import com.apiprices.apiprices.repository.PriceRepository;
import com.apiprices.apiprices.request.PriceRequestParams;
import com.apiprices.apiprices.response.PricesAppliedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PriceService {

    @Autowired
    private PriceRepository repository;

   public PricesAppliedResponse getPriceToApply(PriceRequestParams priceRequestParams) throws ApiException {
       Objects.requireNonNull(priceRequestParams.getAppliedDate(), "appliedDate is required");
       Objects.requireNonNull(priceRequestParams.getBrandId(), "brandId is required");
       Objects.requireNonNull(priceRequestParams.getProductId(), "productId is required");

       try {
           List<Price> pricesToApply = repository.getPriceToApply(priceRequestParams.getAppliedDate(),
                   priceRequestParams.getBrandId(), priceRequestParams.getProductId());
           return setPriceResponse(pricesToApply);
       } catch (Exception e) {
           String error = String.format("[action:getPriceToApply][error_message:%s]", e.getMessage());
           throw new ApiException("500", error);
       }
   }

    private PricesAppliedResponse setPriceResponse(List<Price> price) {
        PricesAppliedResponse pricesAppliedResponse = new PricesAppliedResponse();
        if (price.isEmpty()) {
            return pricesAppliedResponse;
        }

        pricesAppliedResponse.setPrice(price.get(0).getPrice());
        pricesAppliedResponse.setBrandId(price.get(0).getBrandId());
        pricesAppliedResponse.setProductId(price.get(0).getProductId());
        pricesAppliedResponse.setStartDate(price.get(0).getStartDate());
        pricesAppliedResponse.setEndDate(price.get(0).getEndDate());
        return pricesAppliedResponse;
    }
}


