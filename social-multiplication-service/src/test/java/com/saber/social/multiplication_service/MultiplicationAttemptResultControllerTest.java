package com.saber.social.multiplication_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saber.social.multiplication_service.dto.*;
import com.saber.social.multiplication_service.service.MultiplicationService;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.hibernate.validator.constraints.EAN;
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

import java.util.List;

@WebMvcTest
public class MultiplicationAttemptResultControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationResultAttempt> jsonResultAttemptJacksonTester;
    private JacksonTester<StatsAttemptUserDto> jsonStatsAttemptUserDto;
//    private JacksonTester<ResultResponse> jsonResultResponse;

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

        MultiplicationResultAttempt multiplicationResultAttempt = MultiplicationResultAttempt.builder()
                .multiplication(multiplication)
                .user(user)
                .resultAttempt(3500)
                .correct(correct)
                .build();

        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCorrect(correct);


        MultiplicationResultAttempt attemptResponse = MultiplicationResultAttempt.builder()
                .multiplication(multiplication)
                .user(user)
                .resultAttempt(multiplicationResultAttempt.getResultAttempt())
                .correct(correct)
                .build();


        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/results")
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(jsonResultAttemptJacksonTester.
                write(multiplicationResultAttempt).getJson()))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString())
                .isEqualTo(jsonResultAttemptJacksonTester.write(attemptResponse).getJson());

    }

    @Test
    public void getStats() throws Exception {

        User user= new User();
        user.setAlias("saber");

        Multiplication multiplication = new Multiplication();
        multiplication.setFactorA(50);
        multiplication.setFactorB(70);

        MultiplicationResultAttempt attempt1 = MultiplicationResultAttempt.builder()
                .user(user)
                .multiplication(multiplication)
                .correct(true)
                .resultAttempt(3500)
                .build();
        MultiplicationResultAttempt attempt2 = MultiplicationResultAttempt.builder()
                .user(user)
                .multiplication(multiplication)
                .correct(false)
                .resultAttempt(3520)
                .build();

        List<MultiplicationResultAttempt> resultAttempts = Lists.list(attempt1,attempt2);

        StatsAttemptUserDto statsAttemptUserDto= new StatsAttemptUserDto();
        statsAttemptUserDto.setResultAttempts(resultAttempts);

        Mockito.when(multiplicationService.getStatsForUser("saber"))
                .thenReturn(resultAttempts);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                get("/results").param("alias","saber"))
                .andReturn().getResponse();


        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        Assertions.assertThat(response.getContentAsString())
                .isEqualTo(jsonStatsAttemptUserDto.write(statsAttemptUserDto).getJson());

    }

}
