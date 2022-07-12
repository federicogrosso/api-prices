package com.apiprices.apiprices.service;

import com.apiprices.apiprices.exception.ApiException;
import com.apiprices.apiprices.exception.PriceNotFoundException;
import com.apiprices.apiprices.model.Price;
import com.apiprices.apiprices.repository.PriceRepository;
import com.apiprices.apiprices.dto.PriceRequestParams;
import com.apiprices.apiprices.dto.PricesAppliedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository repository;

   public PricesAppliedResponse getPriceToApply(PriceRequestParams priceRequestParams) throws ApiException {
       List<Price> pricesFound;
       try {
          pricesFound = repository.getPriceToApply(
                   priceRequestParams.getAppliedDate(),
                   priceRequestParams.getBrandId(),
                   priceRequestParams.getProductId());
       } catch (Exception e) {
           String error = String.format("[action:getPriceToApply][error_message:%s]", e.getMessage());
           throw new ApiException("500", error);
       }
       return buildPriceResponse(pricesFound);
   }

    private PricesAppliedResponse buildPriceResponse(List<Price> pricesList) {
        if (pricesList.isEmpty()) {
            throw new PriceNotFoundException("Price not found", "not_found");
        }
        return PricesAppliedResponse.build(pricesList.get(0));
    }
}


