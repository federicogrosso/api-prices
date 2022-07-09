package com.apiprices.apiprices.request;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
public class PriceRequestParams {

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime appliedDate;

    @NotNull
    private Long productId;

    @NotNull
    private Long brandId;
    
}