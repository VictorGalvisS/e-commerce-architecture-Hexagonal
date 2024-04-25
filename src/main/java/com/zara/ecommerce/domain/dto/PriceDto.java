package com.zara.ecommerce.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zara.ecommerce.infrastructure.entities.PriceEntity;
import com.zara.ecommerce.infrastructure.exception.InformationNotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.zara.ecommerce.infrastructure.util.Constant.FORMAT_TIMESTAMP;

@Getter
@Builder
@ToString
public class PriceDto implements Serializable {

    private final Long id;
    private final Long brandId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_TIMESTAMP)
    private final LocalDateTime startDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_TIMESTAMP)
    private final LocalDateTime endDate;
    private final Integer priceList;
    private final Long productId;
    private final Integer priority;
    private final BigDecimal price;
    private final String curr;

    public PriceDto(Long id, Long brandId, LocalDateTime startDate, LocalDateTime endDate,
                    Integer priceList, Long productId, Integer priority, BigDecimal price, String curr) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public static List<PriceDto> newInstancesBrandDtos(List<PriceEntity> priceEntityList) {
        List<PriceDto> priceDtos = new ArrayList<>();
        if (priceEntityList.isEmpty()) {
            return priceDtos;
        }
        for (PriceEntity priceEntity : priceEntityList) {
            priceDtos.add(PriceDto.newInstanceBrandDto(priceEntity));
        }
        return priceDtos;
    }

    public static PriceDto newInstanceBrandDto(PriceEntity priceEntity) {
        if (priceEntity == null) {
            throw new InformationNotFoundException("Information price not found");
        }
        return new PriceDto(
                priceEntity.getId(),
                priceEntity.getBrand().getId(),
                priceEntity.getStartDate(),
                priceEntity.getEndDate(),
                priceEntity.getPriceList(),
                priceEntity.getProductId(),
                priceEntity.getPriority(),
                priceEntity.getPrice(),
                priceEntity.getCurr()
        );
    }


}