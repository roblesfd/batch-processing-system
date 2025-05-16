package org.fernandodev.core;

import org.fernandodev.validators.CsvValidator;
import org.fernandodev.validators.FileValidator;
import org.fernandodev.validators.JsonValidator;
import org.fernandodev.validators.XmlValidator;

import java.util.Optional;

public class ValidatorFactory {
    public static Optional<FileValidator> getValidator(String extension){
        return switch(extension){
            case "csv" -> Optional.of(new CsvValidator());
            case "json" -> Optional.of(new JsonValidator());
            case "xml" -> Optional.of(new XmlValidator());
            default -> Optional.empty();
        };
    }
}
