package com.zara.ecommerce.infrastructure.repositories;

import com.zara.ecommerce.infrastructure.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandEntityRepository extends JpaRepository<BrandEntity, Long> {
}