package com.zara.ecommerce.application.usecases;

import com.zara.ecommerce.domain.dto.PriceDto;
import com.zara.ecommerce.infrastructure.exception.InformationNotFoundException;
import com.zara.ecommerce.application.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PriceServiceUseCaseImplTest {

    @Autowired
    private PriceService priceService;

    public static final long PRODUCT_ID = 35455L;
    public static final long BRAND_ID = 1L;

    @Test
    void test1FindPrice() {
        String priceDate = "2020-06-14 10:00:00";
        PriceDto priceDto = priceService.findPrice(BRAND_ID, PRODUCT_ID, priceDate);
        assertEquals(priceDto.getPrice(), new BigDecimal("35.50"));
    }

    @Test
    void test2FindAllPrice() {
        String priceDate = "2020-06-14 10:00:00";
        List<PriceDto> priceDtos = priceService.findAllPrice(
                BRAND_ID, PRODUCT_ID, priceDate
        );
        assertEquals(priceDtos.size(), 1);
    }

    @Test
    void test2NotFindPrice() {
        try {
            String priceDate = "2024-06-14 10:00:00";
            List<PriceDto> priceDtos = priceService.findAllPrice(
                    BRAND_ID, PRODUCT_ID, priceDate
            );
        } catch (Exception e) {
            final String expected = "Information not found.";
            assertEquals(InformationNotFoundException.class, e.getClass());
            assertEquals(expected, e.getMessage());
        }
    }
}
