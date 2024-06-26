package com.zara.ecommerce.infrastructure.repositories;

import com.zara.ecommerce.infrastructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceEntityRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.brand.id=:brandId AND p.productId=:productId AND :applicationDate BETWEEN p.startDate AND p.endDate")
    List<PriceEntity> findPriceByParams(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("applicationDate") LocalDateTime applicationDate
            );
}