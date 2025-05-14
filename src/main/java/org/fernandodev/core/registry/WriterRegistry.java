package org.fernandodev.core.registry;

import org.fernandodev.core.writers.CsvWriter;
import org.fernandodev.core.writers.Writer;
import org.fernandodev.core.writers.JsonWriter;
import org.fernandodev.core.writers.XmlWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WriterRegistry {
    private static final Map<String, Writer> writers = new HashMap<>();

    static {
        writers.put("csv", new CsvWriter());
        writers.put("json", new JsonWriter());
        writers.put("xml", new XmlWriter());
    }

    public static Optional<Writer> getWriter(String extension){
        return Optional.ofNullable(writers.get(extension.toLowerCase()));
    }
}
