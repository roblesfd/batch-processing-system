package org.fernandodev.core;

import org.fernandodev.model.ProcessingResult;

import java.io.File;
import java.util.Optional;

public class BatchRunner {
    private final String inputPath;
    private final String outputPath;

    public BatchRunner(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
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
            } else {
                System.out.printf("❌ [%s] Formato inválido o tipo no soportado.%n", file.getName());
            }
        }
    }

    private String getFileExtension(String name){
        int index = name.lastIndexOf(".");
        return index != -1 ? name.substring(index+1).toLowerCase() : "";
    }
}
