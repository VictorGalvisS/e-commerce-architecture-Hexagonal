package com.zara.ecommerce.domain.ports.in;

import com.zara.ecommerce.domain.dto.PriceDto;

import java.util.List;

public interface PriceServiceUseCase {

    PriceDto findPrice(
            Long brandId, Long productId, String stringPriceDate
    );

    List<PriceDto> findAllPrice(
            Long brandId, Long productId, String stringPriceDate
    );

}
