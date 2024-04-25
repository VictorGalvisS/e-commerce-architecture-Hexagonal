package com.zara.ecommerce.infrastructure.repositories;

import com.zara.ecommerce.domain.ports.out.BrandEntityRepositoryPort;
import com.zara.ecommerce.infrastructure.entities.BrandEntity;
import com.zara.ecommerce.infrastructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandEntityRepositoryAdapter implements BrandEntityRepositoryPort {

    private final BrandEntityRepository brandEntityRepository;

    public BrandEntityRepositoryAdapter(BrandEntityRepository brandEntityRepository) {
        this.brandEntityRepository = brandEntityRepository;
    }
}