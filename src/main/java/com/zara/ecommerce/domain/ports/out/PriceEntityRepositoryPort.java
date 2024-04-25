package com.zara.ecommerce.domain.ports.out;

import com.zara.ecommerce.infrastructure.entities.PriceEntity;

import java.util.List;

public interface PriceEntityRepositoryPort {
    List<PriceEntity> findPriceByParams(Long brandId, Long productId, String stringPriceDate);
}
