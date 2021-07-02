package com.saber.social.multiplication_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.service.MultiplicationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
public class MultiplicationControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<Multiplication> json;

    @BeforeEach
    void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void getRandomMultiplicationTest() throws Exception {

        Multiplication multiplication = new Multiplication();
        multiplication.setFactorA(70);
        multiplication.setFactorB(20);

        Mockito.when(multiplicationService.createRandomMultiplication())
                .thenReturn(multiplication);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/multiplications/random")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        Assertions.assertThat(response.getContentAsString())
                .isEqualTo(json.write(multiplication).getJson());

    }
}