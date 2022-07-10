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
       try {
           List<Price> pricesList = repository.getPriceToApply(priceRequestParams.getAppliedDate(),
                   priceRequestParams.getBrandId(), priceRequestParams.getProductId());
           if (pricesList.isEmpty()) {
               return PricesAppliedResponse.build(new Price());
           }
           return setPriceResponse(pricesList);
       } catch (Exception e) {
           String error = String.format("[action:getPriceToApply][error_message:%s]", e.getMessage());
           throw new ApiException("500", error);
       }
   }

    private PricesAppliedResponse setPriceResponse(List<Price> priceList) {
        return PricesAppliedResponse.build(priceList.get(0));
    }
}


