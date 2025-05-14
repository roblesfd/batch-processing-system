package org.fernandodev.core.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonParser implements Parser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JsonNode parse(File file) throws IOException {
        return objectMapper.readTree(file);
    }
}
