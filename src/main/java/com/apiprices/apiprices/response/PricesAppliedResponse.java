package com.apiprices.apiprices.response;

import com.apiprices.apiprices.model.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class PricesAppliedResponse {

    @JsonProperty("brand_id")
    private Long brandId;

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("price")
    private Float price;

    public static PricesAppliedResponse build(Price price) {
        return PricesAppliedResponse.builder()
                .brandId(price.getBrandId())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .productId(price.getProductId())
                .price(price.getPrice())
                .build();
    }
}
