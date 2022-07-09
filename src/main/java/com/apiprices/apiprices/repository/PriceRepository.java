package com.apiprices.apiprices.repository;

import com.apiprices.apiprices.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select new com.apiprices.apiprices.model.Price(p.priceId, p.brandId, p.startDate, p.endDate, p.priceList, p.productId, p.priority, p.price, p.curr) "
            + "from Price p "
            + "where (p.startDate<=:appliedDate and p.endDate>=:appliedDate) and p.brandId=:brandId and p.productId=:productId "
            + "order by p.priority desc")
    List<Price> getPriceToApply(@Param("appliedDate") LocalDateTime appliedDate,
                                @Param("brandId") Long brandId,
                                @Param("productId") Long productId);

}
