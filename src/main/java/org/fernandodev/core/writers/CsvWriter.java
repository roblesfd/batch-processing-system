package org.fernandodev.core.writers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class CsvWriter implements Writer {

    private final CsvMapper csvMapper = new CsvMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void write(JsonNode data, File outputFile) throws IOException {
        ArrayNode array = null;

        if (data.isArray()) {
            array = (ArrayNode) data;
        } else if (data.isObject() && data.size() == 1) {
            JsonNode possibleArray = data.elements().next();
            if (possibleArray.isArray()) {
                array = (ArrayNode) possibleArray;
            }
        }

        if (array == null || array.isEmpty()) {
            System.out.println("⚠️ Nada que escribir: no se encontró un array de objetos.");
            return;
        }

        // Aplanar todos los objetos
        ArrayNode flattenedArray = objectMapper.createArrayNode();
        for (JsonNode row : array) {
            if (row.isObject()) {
                ObjectNode flat = flatten((ObjectNode) row);
                flattenedArray.add(flat);
            }
        }

        if (flattenedArray.isEmpty()) {
            System.out.println("⚠️ No hay objetos válidos para escribir.");
            return;
        }

        // Construir schema desde el primer objeto aplanado
        ObjectNode first = (ObjectNode) flattenedArray.get(0);
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        first.fieldNames().forEachRemaining(schemaBuilder::addColumn);
        CsvSchema schema = schemaBuilder.setUseHeader(true).build();

        // Escribir el archivo
        csvMapper.writerFor(JsonNode.class)
                .with(schema)
                .writeValue(outputFile, flattenedArray);
    }

    private ObjectNode flatten(ObjectNode node) {
        ObjectNode flat = objectMapper.createObjectNode();
        flattenRecursive("", node, flat);
        return flat;
    }

    private void flattenRecursive(String prefix, JsonNode current, ObjectNode target) {
        if (current.isObject()) {
            Iterator<String> fieldNames = current.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode value = current.get(fieldName);
                String newKey = prefix.isEmpty() ? fieldName : prefix + "." + fieldName;
                flattenRecursive(newKey, value, target);
            }
        } else if (current.isArray()) {
            // Convertimos arrays a JSON string para CSV
            target.put(prefix, current.toString());
        } else {
            target.set(prefix, current);
        }
    }
}
