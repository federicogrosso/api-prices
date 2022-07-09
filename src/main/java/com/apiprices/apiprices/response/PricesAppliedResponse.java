package com.apiprices.apiprices.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PricesAppliedResponse {

    private Long brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long productId;

    private Float price;

}
