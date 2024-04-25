package com.zara.ecommerce.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
public class PriceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "field cannot be empty")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BrandEntity brand;

    @NotNull(message = "field cannot be empty")
    @Column(name = "START_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @NotNull(message = "field cannot be empty")
    @Column(name = "END_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;

    @NotNull(message = "field cannot be empty")
    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;

    @NotNull(message = "field cannot be empty")
    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @NotNull
    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @NotNull
    @Column(name = "PRICE", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @Size(max = 3)
    @NotNull
    @Column(name = "CURR", nullable = false, length = 5)
    private String curr;

    public Long getId() {
        return id;
    }


    public BrandEntity getBrand() {
        return brand;
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }


    public LocalDateTime getEndDate() {
        return endDate;
    }


    public Integer getPriceList() {
        return priceList;
    }


    public Long getProductId() {
        return productId;
    }


    public Integer getPriority() {
        return priority;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public String getCurr() {
        return curr;
    }

}
