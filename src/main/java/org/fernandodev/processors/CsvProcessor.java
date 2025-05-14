package org.fernandodev.processors;

import org.fernandodev.core.FileProcessor;
import org.fernandodev.model.ProcessingResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class CsvProcessor implements FileProcessor {
    @Override
    public ProcessingResult process(File file){
        try(Stream<String> lines = Files.lines(file.toPath())) {
            long count = lines.count();
            return new ProcessingResult(true, "Archivo CSV procesado correctamente. Lineas: " + count);
        }catch(IOException e) {
            return new ProcessingResult(false, "Error leyendo archivo CSV: " + e.getMessage());
        }
    }
}
