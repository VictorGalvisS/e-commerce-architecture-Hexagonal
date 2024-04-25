package com.zara.ecommerce.infrastructure.config;


import com.zara.ecommerce.application.service.PriceService;
import com.zara.ecommerce.application.usecases.PriceServiceUseCaseImpl;
import com.zara.ecommerce.domain.ports.out.PriceEntityRepositoryPort;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Technical test (Zara)")
                        .version("Store Price")
                        .description("Validate Price and test endpoint")
                        .contact(new Contact().name("Victor Galvis").email("victorGalviss1987@gmail.com"))
                );
    }

    @Bean
    public PriceService taskService(PriceEntityRepositoryPort priceEntityRepositoryPort) {
        return new PriceService(
                new PriceServiceUseCaseImpl(priceEntityRepositoryPort)
        );
    }
}
