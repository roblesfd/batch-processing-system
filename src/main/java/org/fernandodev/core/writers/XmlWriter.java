package org.fernandodev.core.writers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class XmlWriter implements Writer {

    private final XmlMapper xmlMapper = new XmlMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void write(JsonNode data, File outputFile) throws IOException {
        List<Map<String, Object>> records = new ArrayList<>();

        if (data.isArray()) {
            for (JsonNode node : data) {
                records.add(objectMapper.convertValue(node, LinkedHashMap.class));
            }
        } else {
            records.add(objectMapper.convertValue(data, LinkedHashMap.class));
        }

        RecordsWrapper wrapper = new RecordsWrapper(records);
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, wrapper);
    }
}
