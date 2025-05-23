package org.fernandodev.processors;

import org.fernandodev.model.ProcessingResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class JsonProcessor implements FileProcessor {
    @Override
    public ProcessingResult process(File file) {
        try(Stream<String> lines = Files.lines(file.toPath())) {
            long count = lines.count();
            return new ProcessingResult(true, "Archivo procesado correctamente. Lineas: " + count);
        }catch(IOException e){
            return new ProcessingResult(false, "Error al procesar el archivo .json: " + e.getMessage());
        }
    }
}
