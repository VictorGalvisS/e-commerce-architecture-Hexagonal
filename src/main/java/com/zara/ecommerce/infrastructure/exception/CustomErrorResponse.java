package com.zara.ecommerce.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomErrorResponse {
    private String message;
    private String description;
    private LocalDateTime timestamp;
}
