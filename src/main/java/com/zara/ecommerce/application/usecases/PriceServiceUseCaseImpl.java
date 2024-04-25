package com.zara.ecommerce.application.usecases;

import com.zara.ecommerce.domain.dto.PriceDto;
import com.zara.ecommerce.domain.ports.in.PriceServiceUseCase;
import com.zara.ecommerce.domain.ports.out.PriceEntityRepositoryPort;
import com.zara.ecommerce.infrastructure.entities.PriceEntity;
import com.zara.ecommerce.infrastructure.exception.InformationNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;

import static com.zara.ecommerce.infrastructure.util.Constant.DATE_TIME_FORMATTER;

@Service
public class PriceServiceUseCaseImpl implements PriceServiceUseCase {

    private static final Logger log = LoggerFactory.getLogger(PriceServiceUseCaseImpl.class);
    private final PriceEntityRepositoryPort priceRepository;

    public PriceServiceUseCaseImpl(PriceEntityRepositoryPort priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceDto findPrice(Long brandId, Long productId, String stringPriceDate) {
        validateInformation(brandId, productId, stringPriceDate);
        List<PriceEntity> prices = priceRepository.findPriceByParams(brandId, productId, stringPriceDate);
        if (prices.isEmpty()) {
            throw new InformationNotFoundException("Information not found.");
        }
        prices.sort(Comparator.comparing(PriceEntity::getPriority).reversed());
        return PriceDto.newInstanceBrandDto(prices.get(0));
    }


    @Override
    public List<PriceDto> findAllPrice(Long brandId, Long productId, String stringPriceDate) {
        List<PriceEntity> prices = findAllPriceByParams(brandId, productId, stringPriceDate);
        return PriceDto.newInstancesBrandDtos(prices);
    }

    private List<PriceEntity> findAllPriceByParams(Long brandId, Long productId, String stringPriceDate) {
        validateInformation(brandId, productId, stringPriceDate);
        List<PriceEntity> prices = priceRepository.findPriceByParams(brandId, productId, stringPriceDate);
        if (prices.isEmpty()) {
            throw new InformationNotFoundException("Information not found.");
        }
        prices.sort(Comparator.comparing(PriceEntity::getPriority));
        return prices;
    }

    private void validateInformation(Long brandId, Long productId, String stringPriceDate) {
        String msgError = "Parameters productId or brandId Not Found Data";
        if (productId == null || brandId == 0) {
            log.error(msgError);
            throw new InformationNotFoundException(msgError);
        }

        if (productId <= 0 || brandId <= 0) {
            log.error(msgError);
            throw new InformationNotFoundException(msgError);
        }

        LocalDateTime priceDate = null;
        try {
            priceDate = LocalDateTime.parse(stringPriceDate, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            log.error( "Parameter priceDate Invalid Information");
            throw e;
        }
    }
}
