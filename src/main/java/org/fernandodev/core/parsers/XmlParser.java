package org.fernandodev.core.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XmlParser implements Parser {

    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public JsonNode parse(File file) throws IOException {
        return xmlMapper.readTree(file);
    }
}
