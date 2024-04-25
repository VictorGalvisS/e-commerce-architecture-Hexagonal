package com.zara.ecommerce.application.service;

import com.zara.ecommerce.domain.dto.PriceDto;
import com.zara.ecommerce.domain.ports.in.PriceServiceUseCase;

import java.util.List;

public class PriceService implements PriceServiceUseCase {

    private final PriceServiceUseCase priceServiceUseCase;

    public PriceService(PriceServiceUseCase priceServiceUseCase) {
        this.priceServiceUseCase = priceServiceUseCase;
    }

    @Override
    public PriceDto findPrice(Long brandId, Long productId, String stringPriceDate) {
        return priceServiceUseCase.findPrice(brandId, productId, stringPriceDate);
    }

    @Override
    public List<PriceDto> findAllPrice( Long brandId, Long productId, String stringPriceDate ){
        return priceServiceUseCase.findAllPrice(brandId, productId, stringPriceDate);
    }


}
