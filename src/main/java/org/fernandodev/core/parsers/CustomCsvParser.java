package org.fernandodev.core.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class CustomCsvParser implements Parser {

    private final CsvMapper csvMapper = new CsvMapper();
    private final CsvSchema schema = CsvSchema.emptySchema().withHeader();

    @Override
    public JsonNode parse(File file) throws IOException {
        Iterator<Map<String, String>> iterator =
                csvMapper.readerFor(Map.class)
                         .with(schema)
                         .readValues(new FileInputStream(file));

        ArrayNode arrayNode = new ObjectMapper().createArrayNode();
        while (iterator.hasNext()) {
            Map<String, String> row = iterator.next();
            JsonNode node = new ObjectMapper().valueToTree(row);
            arrayNode.add(node);
        }
        return arrayNode;
    }
}
