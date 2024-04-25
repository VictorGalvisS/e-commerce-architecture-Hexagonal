package com.zara.ecommerce.infrastructure.controllers;

import com.zara.ecommerce.domain.dto.PriceDto;
import com.zara.ecommerce.infrastructure.exception.CustomErrorResponse;
import com.zara.ecommerce.application.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("price")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Operation(summary = "Find price", description = "Find a pvp single price", tags = {"price"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data supplied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @GetMapping("/findPrice")
    public PriceDto findPrice(@Parameter(example = "1") @RequestParam("brandId") Long brandId,
                              @Parameter(example = "35455") @RequestParam("productId") Long productId,
                              @Parameter(example = "2020-06-14 10:00:00") @RequestParam("applicationDate") String applicationDate) {
        return priceService.findPrice(brandId, productId, applicationDate);
    }

    @Operation(summary = "Find All price", description = "Find a pvp single price", tags = {"price"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data supplied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @GetMapping("/findAllPrice")
    public List<PriceDto> findAllPrice(@Parameter(example = "1") @RequestParam("brandId") Long brandId,
                                       @Parameter(example = "35455") @RequestParam("productId") Long productId,
                                       @Parameter(example = "2020-06-14 10:00:00") @RequestParam("applicationDate") String applicationDate) {
        return priceService.findAllPrice(brandId, productId, applicationDate);
    }
}
