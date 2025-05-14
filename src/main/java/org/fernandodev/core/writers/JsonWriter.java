package org.fernandodev.core.writers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonWriter implements Writer {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void write(JsonNode data, File outputFile) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, data);
    }
}
