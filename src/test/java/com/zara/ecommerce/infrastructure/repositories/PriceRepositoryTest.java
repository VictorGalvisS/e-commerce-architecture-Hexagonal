package com.zara.ecommerce.infrastructure.repositories;

import com.zara.ecommerce.infrastructure.entities.PriceEntity;
import com.zara.ecommerce.infrastructure.repositories.PriceEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static com.zara.ecommerce.infrastructure.util.Constant.DATE_TIME_FORMATTER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PriceRepositoryTest {
    private static final long PRODUCT_ID = 35455;
    public static final long BRAND_ID = 1;
    @Autowired
    private PriceEntityRepository priceRepository;

    private LocalDateTime getPriceDate(String applicationDate){
        return  LocalDateTime.parse(applicationDate, DATE_TIME_FORMATTER);
    }

    /**
     * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void test1findPriceByParams() {
        List<PriceEntity> priceByParams = priceRepository.findPriceByParams(
                BRAND_ID, PRODUCT_ID, getPriceDate("2020-06-14 10:00:00"));
        assertEquals(priceByParams.size(), 1);
    }

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void test2findPriceByParams() {
        List<PriceEntity> priceByParams = priceRepository.findPriceByParams(
                BRAND_ID, PRODUCT_ID, getPriceDate("2020-06-14 16:00:00"));
        assertEquals(priceByParams.size(), 2);
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void test3findPriceByParams() {
        List<PriceEntity> priceByParams = priceRepository.findPriceByParams(
                BRAND_ID, PRODUCT_ID, getPriceDate("2020-06-14 21:00:00"));
        assertEquals(priceByParams.size(), 1);
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void test4findPriceByParams() {
        List<PriceEntity> priceByParams = priceRepository.findPriceByParams(
                BRAND_ID, PRODUCT_ID, getPriceDate("2020-06-15 10:00:00"));
        assertEquals(priceByParams.size(), 2);
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void test5findPriceByParams() {
        List<PriceEntity> priceByParams = priceRepository.findPriceByParams(
                BRAND_ID, PRODUCT_ID, getPriceDate("2020-06-16 21:00:00"));
        assertEquals(priceByParams.size(), 2);
    }

    /**
     * Test 6
     */
    @Test
    public void test6findAllPriceByParams() {
        List<PriceEntity> priceByParams = priceRepository.findPriceByParams(
                BRAND_ID, PRODUCT_ID, getPriceDate("2021-01-01 16:10:00"));
        assertEquals(priceByParams.size(), 2);
    }
}
