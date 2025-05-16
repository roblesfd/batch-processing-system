package org.fernandodev.core;

import org.fernandodev.model.ProcessingResult;
import org.fernandodev.repositories.ProcessingResultRepository;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BatchRunner {
    private final String inputPath;
    private final String outputPath;
    private final ProcessingResultRepository repository;

    public BatchRunner(String inputPath, String outputPath ) throws SQLException {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.repository = new ProcessingResultRepository();
    }

    public void run() {
        File inputDir = new File(inputPath);
        File[] files = null;
        verifyIfValidDirectory(inputDir, files);

        List<CompletableFuture<Void>> futures;
        try (ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {

            futures = Arrays.stream(files)
                    .map(file -> CompletableFuture.supplyAsync(() -> processFile(file), executor)
                            .thenAccept(result -> handleResult(result, file))
                            .exceptionally(ex -> handleProcessingError(file, ex)))
                    .toList();
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private void verifyIfValidDirectory(File inputDir, File[] files) {
        if(!inputDir.exists() || !inputDir.isDirectory()) {
            System.err.println("❌ Directorio de entrada no válido");
            return;
        }

        files = inputDir.listFiles();
        if(files == null || files.length == 0) {
            System.out.println("ℹ\uFE0F No hay archivos para procesar");
            return;
        }
    }

    private ProcessingResult processFile(File file) {
        String extension = getFileExtension(file.getName());
        return ValidatorFactory.getValidator(extension)
                .filter(validator -> validator.isValid(file))
                .flatMap(valid -> ProcessorFactory.getProcessor(extension))
                .map(processor -> processor.process(file))
                .orElse(null);
    }

    private String getFileExtension(String name) {
        int index = name.lastIndexOf(".");
        return index != -1 ? name.substring(index+1).toLowerCase() : "";
    }

    private void handleResult(ProcessingResult result, File file) {
        if (result != null) {
            System.out.printf("✅ [%s] %s%n", file.getName(), result.message());
            repository.save(file.getName(), "SUCCESS", result.message(), LocalDateTime.now());
        } else {
            System.out.printf("❌ [%s] Formato invalido o  tipo no soportado. %n", file.getName());
            repository.save(file.getName(), "FAILED", "Formato invalido o tipo no soportado", LocalDateTime.now());
        }
    }

    private Void handleProcessingError(File file, Throwable ex) {
        System.err.printf("[%s] Error de procesamiento: %s%n", file.getName(), ex.getMessage());
        repository.save(file.getName(), "ERROR", ex.getMessage(), LocalDateTime.now());
        return null;
    }
}
