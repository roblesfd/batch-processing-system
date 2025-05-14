package org.fernandodev.core;

import org.fernandodev.processors.CsvProcessor;
import org.fernandodev.processors.JsonProcessor;
import org.fernandodev.processors.XmlProcessor;

import java.util.Optional;

public class ProcessorFactory {
    public static Optional<FileProcessor> getProcessor(String extension){
        return switch(extension){
            case "csv" -> Optional.of(new CsvProcessor());
            case "json" -> Optional.of(new JsonProcessor());
            case "xml" -> Optional.of(new XmlProcessor());
            default -> Optional.empty();
        };
    }
}