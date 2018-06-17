package com.yefan.study.spring.reactive.json.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yefan.study.spring.reactive.domain.Person;

import java.io.IOException;
import java.util.Optional;

public class PersonReader {

    private static final ObjectMapper JSON = new ObjectMapper();

    public static Optional<Person> read(String value) {
        try {
            final JsonNode node = JSON.readTree(value);

            return Optional.of(new Person(
                    node.get("id").asText(),
                    node.get("firstName").asText(),
                    node.get("familyName").asText(),
                    node.get("age").asInt()
            ));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
