package org.fernandodev.core;

import org.fernandodev.model.ProcessingResult;
import org.fernandodev.repositories.ProcessingResultRepository;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class BatchRunner {
    private final String inputPath;
    private final String outputPath;
    private final ProcessingResultRepository repository;

    public BatchRunner(String inputPath, String outputPath ) throws SQLException {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.repository = new ProcessingResultRepository();
    }

    public void run(){
        File inputDir = new File(inputPath);
        if(!inputDir.exists() || !inputDir.isDirectory()) {
            System.err.println("Directorio de entrada no válido");
            return;
        }

        File[] files = inputDir.listFiles();
        if(files == null || files.length == 0) {
            System.out.println("No hay archivos para procesar");
            return;
        }

        for(File file: files) {
            String extension = getFileExtension(file.getName());
            Optional<ProcessingResult> resultOpt = ValidatorFactory.getValidator(extension)
                    .filter(validator -> validator.isValid(file))
                    .flatMap(valid -> ProcessorFactory.getProcessor(extension))
                    .map(processor -> processor.process(file));

            if (resultOpt.isPresent()) {
                System.out.printf("✅ [%s] %s%n", file.getName(), resultOpt.get().message());
                this.repository.save(file.getName(), "SUCCESS", resultOpt.get().message(), LocalDateTime.now());
            } else {
                System.out.printf("❌ [%s] Formato inválido o tipo no soportado.%n", file.getName());
                this.repository.save(file.getName(), "FAILED", "Formato inválido o tipo no soportado", LocalDateTime.now());
            }
        }
    }

    private String getFileExtension(String name){
        int index = name.lastIndexOf(".");
        return index != -1 ? name.substring(index+1).toLowerCase() : "";
    }
}
