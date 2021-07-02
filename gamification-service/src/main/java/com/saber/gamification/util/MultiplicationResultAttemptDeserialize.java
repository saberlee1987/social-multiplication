package com.saber.gamification.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.saber.gamification.dto.MultiplicationResultAttempt;

import java.io.IOException;

public class MultiplicationResultAttemptDeserialize extends JsonDeserializer<MultiplicationResultAttempt> {
    @Override
    public MultiplicationResultAttempt deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        if (jsonNode == null) {
            return null;
        }

        MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt();
        JsonNode userNode = jsonNode.get("user");
        resultAttempt.setUserAlias(userNode.get("alias").asText("user not found"));
        resultAttempt.setCorrect(jsonNode.get("correct").asBoolean());
        JsonNode multiplicationJson = jsonNode.get("multiplication");
        resultAttempt.setFactorA(multiplicationJson.get("factorA").asInt(0));
        resultAttempt.setFactorB(multiplicationJson.get("factorB").asInt(0));
        resultAttempt.setResultAttempt(jsonNode.get("resultAttempt").asInt(0));
        return resultAttempt;
    }
}
