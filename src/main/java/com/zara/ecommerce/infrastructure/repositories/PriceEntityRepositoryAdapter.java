package com.zara.ecommerce.infrastructure.repositories;

import com.zara.ecommerce.domain.ports.out.PriceEntityRepositoryPort;
import com.zara.ecommerce.infrastructure.entities.PriceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.zara.ecommerce.infrastructure.util.Constant.DATE_TIME_FORMATTER;

@Component
public class PriceEntityRepositoryAdapter implements PriceEntityRepositoryPort {

    private static final Logger log = LoggerFactory.getLogger(PriceEntityRepositoryAdapter.class);

    private final PriceEntityRepository priceEntityRepository;


    public PriceEntityRepositoryAdapter(PriceEntityRepository priceEntityRepository) {
        this.priceEntityRepository = priceEntityRepository;
    }

    @Override
    public List<PriceEntity> findPriceByParams(Long brandId, Long productId, String stringPriceDate) {
        LocalDateTime applicationDate = getPriceData(stringPriceDate);
        return priceEntityRepository.findPriceByParams(brandId, productId, applicationDate);
    }


    private LocalDateTime getPriceData(String stringPriceDate) {
        LocalDateTime priceDate = null;
        try {
            priceDate = LocalDateTime.parse(stringPriceDate, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            String msgError = "Parameter priceDate Invalid Information";
            log.error(msgError);
            throw e;
        }

        return priceDate;
    }
}