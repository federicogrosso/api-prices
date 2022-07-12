package com.apiprices.apiprices.service;

import com.apiprices.apiprices.dto.PriceRequestParams;
import com.apiprices.apiprices.dto.PricesAppliedResponse;
import com.apiprices.apiprices.exception.ApiException;
import com.apiprices.apiprices.exception.PriceNotFoundException;
import com.apiprices.apiprices.model.Price;
import com.apiprices.apiprices.repository.PriceRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class PriceServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private PriceService service;

    @Mock
    private PriceRepository repository;

    @Test
    public void getPriceToApplySuccessfully() throws ApiException {
        // given
        LocalDateTime date = LocalDateTime.parse("2020-06-15T02:59:59.999Z", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T02:59:59.999Z", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endDate = LocalDateTime.parse("2020-06-16T02:59:59.999Z", DateTimeFormatter.ISO_DATE_TIME);
        Long productId = 35455L;
        Long brandId = 1L;
        Long priority1 = 1L;
        Long priority2 = 2L;
        Float priceExpected = 27.5f;

        PriceRequestParams priceRequestParams = new PriceRequestParams(date, productId, brandId);
        List<Price> priceListExpected = new ArrayList<>();
        priceListExpected.add(new Price(1L, 1L,
                startDate, endDate, 1L, productId, priority2, priceExpected, "EUR"));
        priceListExpected.add(new Price(2L, 1L,
                startDate, endDate, 1L, productId, priority1, 25.5f, "EUR"));

        // when
        when(repository.getPriceToApply(date, brandId, productId)).thenReturn(priceListExpected);
        PricesAppliedResponse priceToApply = service.getPriceToApply(priceRequestParams);

        // then
        Assert.assertEquals(priceToApply.getPrice(), priceExpected);
        then(repository).should(times(1)).getPriceToApply(any(), any(), any());
    }

    @Test
    public void getPriceToApplyNotFoundPrices() throws ApiException {
        // given
        LocalDateTime date = LocalDateTime.parse("2020-06-15T02:59:59.999Z", DateTimeFormatter.ISO_DATE_TIME);
        Long productId = 35455L;
        Long brandId = 1L;

        PriceRequestParams priceRequestParams = new PriceRequestParams(date, productId, brandId);
        List<Price> priceListExpected = new ArrayList<>();

        // when
        when(repository.getPriceToApply(date, brandId, productId)).thenReturn(priceListExpected);
        PriceNotFoundException e = Assertions.assertThrows(PriceNotFoundException.class, () -> {
           service.getPriceToApply(priceRequestParams);
        });

        // then
        Assertions.assertEquals("Price not found", e.getMessage());
        then(repository).should(times(1)).getPriceToApply(any(), any(), any());
    }

    @Test
    public void getPriceToApplyRepositoryCallFails() throws ApiException {
        // given
        LocalDateTime date = LocalDateTime.parse("2020-06-15T02:59:59.999Z", DateTimeFormatter.ISO_DATE_TIME);
        Long productId = 35455L;
        Long brandId = 1L;

        PriceRequestParams priceRequestParams = new PriceRequestParams(date, productId, brandId);

        // when
        when(repository.getPriceToApply(date, brandId, productId)).thenThrow(new ApiException());
        ApiException e = Assertions.assertThrows(ApiException.class, () -> {
            service.getPriceToApply(priceRequestParams);
        });

        // then
        Assertions.assertEquals("[action:getPriceToApply][error_message:Internal server error]", e.getMessage());
        then(repository).should(times(1)).getPriceToApply(any(), any(), any());
    }

}