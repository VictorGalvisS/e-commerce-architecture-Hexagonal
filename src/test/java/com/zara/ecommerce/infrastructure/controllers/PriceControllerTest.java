package com.zara.ecommerce.infrastructure.controllers;

import com.zara.ecommerce.infrastructure.exception.InformationNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.time.format.DateTimeParseException;
import java.util.Objects;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public static final long PRODUCT_ID = 35455;
    public static final long BRAND_ID = 1;
    public static final String URL_TEMPLATE = "/price/findPrice";
    public static final String PARAM_BRANDID = "brandId";
    public static final String PARAM_PRODUCTID = "productId";
    public static final String PARAM_APPLICATIONDATE = "applicationDate";

    private RequestBuilder generateRequestBuilder(String applicationDate) {
        return MockMvcRequestBuilders.
                get(URL_TEMPLATE)
                .param(PARAM_BRANDID, String.valueOf(BRAND_ID))
                .param(PARAM_PRODUCTID, String.valueOf(PRODUCT_ID))
                .param(PARAM_APPLICATIONDATE, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void test1GetPriceByApplicationDate_WhenReturnPrice35_50() throws Exception {
        RequestBuilder requestBuilder = generateRequestBuilder("2020-06-14 10:00:00");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50))
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void test2GetPriceByApplicationDate_WhenReturnPrice25_45() throws Exception {
        RequestBuilder requestBuilder = generateRequestBuilder("2020-06-14 16:00:00");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45));
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void test3GetPriceByApplicationDate_WhenReturnPrice35_50() throws Exception {
        RequestBuilder requestBuilder = generateRequestBuilder("2020-06-14 21:00:00");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void test4GetPriceByApplicationDate_WhenReturnPrice30_50() throws Exception {
        RequestBuilder requestBuilder = generateRequestBuilder("2020-06-15 10:00:00");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50));
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void test5GetPriceByApplicationDate_WhenReturnPrice38_95() throws Exception {
        RequestBuilder requestBuilder = generateRequestBuilder("2020-06-16 21:00:00");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95));
    }

    @Test
    public void test6GetPriceByApplicationDate_WhenProductIdNotExist() throws Exception {
        long productIdNotExist = 9999;
        String applicationDate = "2020-06-16 21:00:00";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get(URL_TEMPLATE)
                .param(PARAM_BRANDID, String.valueOf(BRAND_ID))
                .param(PARAM_PRODUCTID, String.valueOf(productIdNotExist))
                .param(PARAM_APPLICATIONDATE, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        assertEquals(InformationNotFoundException.class, Objects.requireNonNull(resultActions.andReturn().getResolvedException()).getClass());
    }

    @Test
    public void test7GetPriceByApplicationDate_WhenBrandIdNotExist() throws Exception {
        long brandId = 9999;
        String applicationDate = "2020-06-16 21:00:00";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get(URL_TEMPLATE)
                .param(PARAM_BRANDID, String.valueOf(brandId))
                .param(PARAM_PRODUCTID, String.valueOf(PRODUCT_ID))
                .param(PARAM_APPLICATIONDATE, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        assertEquals(InformationNotFoundException.class, Objects.requireNonNull(resultActions.andReturn().getResolvedException()).getClass());
    }

    @Test
    public void test8GetPriceByApplicationDate__WhenParamApplicationDateFormatInvalid() throws Exception {
        String applicationDate = "2024-01-01T00:00:0022222222";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get(URL_TEMPLATE)
                .param(PARAM_BRANDID, String.valueOf(BRAND_ID))
                .param(PARAM_PRODUCTID, String.valueOf(PRODUCT_ID))
                .param(PARAM_APPLICATIONDATE, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(DateTimeParseException.class, Objects.requireNonNull(resultActions.andReturn().getResolvedException()).getClass());
    }

    @Test
    public void test9GetPriceByApplicationDate_WhenParamApplicationDateNotExist() throws Exception {

        String applicationDate = "2024-01-01T00:00:0022222222";
        String paramApplicationDateNotExist = "startDate";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get(URL_TEMPLATE)
                .param(PARAM_BRANDID, String.valueOf(BRAND_ID))
                .param(PARAM_PRODUCTID, String.valueOf(PRODUCT_ID))
                .param(paramApplicationDateNotExist, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(MissingServletRequestParameterException.class, Objects.requireNonNull(resultActions.andReturn().getResolvedException()).getClass());
    }

    @Test
    public void test10GetPriceByApplicationDate_WhenParamProductIdNotExist() throws Exception {

        String applicationDate = "2024-01-01T00:00:0022222222";
        String paramProductId = "sss";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get(URL_TEMPLATE)
                .param(PARAM_BRANDID, String.valueOf(BRAND_ID))
                .param(paramProductId, String.valueOf(PRODUCT_ID))
                .param(PARAM_APPLICATIONDATE, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(MissingServletRequestParameterException.class, Objects.requireNonNull(resultActions.andReturn().getResolvedException()).getClass());
    }

    @Test
    public void test11GetPriceByApplicationDate_WhenReturnList() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/price/findAllPrice")
                .param(PARAM_BRANDID, String.valueOf(BRAND_ID))
                .param(PARAM_PRODUCTID, String.valueOf(PRODUCT_ID))
                .param(PARAM_APPLICATIONDATE, "2021-01-01 16:10:00")
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", containsInAnyOrder(5, 6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].price", containsInAnyOrder(45.75, 46.99)));
    }
}
