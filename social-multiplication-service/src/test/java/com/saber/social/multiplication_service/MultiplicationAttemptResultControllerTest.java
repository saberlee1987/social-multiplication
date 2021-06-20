package com.saber.social.multiplication_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.dto.MultiplicationResultAttempt;
import com.saber.social.multiplication_service.dto.ResultResponse;
import com.saber.social.multiplication_service.dto.User;
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
public class MultiplicationAttemptResultControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationResultAttempt> jsonResultAttemptJacksonTester;
    private JacksonTester<ResultResponse> jsonResultResponse;

    @BeforeEach
    void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void postResultCorrect() throws Exception {
        generateParameterizedTest(true);
    }

    @Test
    void postResultWrong() throws Exception {
        generateParameterizedTest(false);
    }

    void generateParameterizedTest(boolean correct) throws Exception {

        Mockito.when(multiplicationService.checkAttempt(
                Mockito.any(MultiplicationResultAttempt.class)))
                .thenReturn(correct);

        User user = new User();
        user.setAlias("saber");

        Multiplication multiplication = new Multiplication();
        multiplication.setFactorB(50);
        multiplication.setFactorA(70);

        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt();
        multiplicationResultAttempt.setMultiplication(multiplication);
        multiplicationResultAttempt.setUser(user);
        multiplicationResultAttempt.setResultAttempt(3500);

        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCorrect(correct);


        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/results")
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(jsonResultAttemptJacksonTester.
                write(multiplicationResultAttempt).getJson()))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString())
                .isEqualTo(jsonResultResponse.write(resultResponse).getJson());

    }

}
