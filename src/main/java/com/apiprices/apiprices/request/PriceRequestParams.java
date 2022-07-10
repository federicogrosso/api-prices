package com.apiprices.apiprices.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
public class PriceRequestParams {

    @NotNull(message = "required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime appliedDate;

    @NotNull(message = "required")
    private Long productId;

    @NotNull(message = "required")
    private Long brandId;
    
}