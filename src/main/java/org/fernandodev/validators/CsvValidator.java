package org.fernandodev.validators;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class CsvValidator implements FileValidator {

    @Override
    public boolean isValid(File file) {
        try (var reader = Files.newBufferedReader(file.toPath());
             var csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            List<String> header = csvParser.getHeaderNames();
            int expectedColumns = header.isEmpty() ? -1 : header.size();

            for (var record : csvParser) {
                if (expectedColumns != -1 && record.size() != expectedColumns) {
                    return false;
                }
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}

