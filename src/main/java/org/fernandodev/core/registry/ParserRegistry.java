package org.fernandodev.core.registry;

import org.fernandodev.core.parsers.CustomCsvParser;
import org.fernandodev.core.parsers.Parser;
import org.fernandodev.core.parsers.JsonParser;
import org.fernandodev.core.parsers.XmlParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParserRegistry {
    private static final Map<String, Parser> parsers = new HashMap<>();
    static {
        parsers.put("csv", new CustomCsvParser());
        parsers.put("json", new JsonParser());
        parsers.put("xml", new XmlParser());
    }

    public static Optional<Parser> getParser(String extension) {
        return Optional.ofNullable(parsers.get(extension.toLowerCase()));
    }
}
