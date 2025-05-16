package org.fernandodev.core.observers;

import org.fernandodev.core.BatchRunner;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DirectoryWatcher {
    private final Path inputPath;
    private final String outputPath;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public DirectoryWatcher(String inputPath, String outputPath) throws IOException {
        this.inputPath = Paths.get(inputPath);
        this.outputPath = outputPath;
    }

    public void start() throws IOException, InterruptedException {
        try(WatchService watchService = FileSystems.getDefault().newWatchService()) {
            inputPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            System.out.println("Observando carpeta: " + inputPath.toAbsolutePath());
            while (true) {
                WatchKey key = watchService.take();
                processNewEvent(key);//Si se agrega un archivo al dir observado, lo procesa

                boolean valid = key.reset();
                if (!valid) break;
            }
        }catch(IOException | InterruptedException e) {
            System.err.printf("❌ Error en DirectoryWatcher: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static WatchEvent<Path> cast(WatchEvent<?> event) {
        return (WatchEvent<Path>) event;
    }

    private void processNewEvent(WatchKey key) {
        for (WatchEvent<?> event : key.pollEvents()) {
            WatchEvent.Kind<?> kind = event.kind();

            if (kind == StandardWatchEventKinds.OVERFLOW) {continue;}

            WatchEvent<Path> ev = cast(event);
            Path filename = ev.context();

            System.out.printf("📄 Nuevo archivo detectado: %s%n", filename);

            processFileAsync(filename);
        }
    }

    private void processFileAsync(Path filename){
        executor.submit(() -> {
            try {
                new BatchRunner(inputPath.toString(), outputPath).run();
            } catch (Exception e) {
                System.err.printf("❌ Error al procesar el archivo %s: %s%n", filename, e.getMessage());
            }
        });
    }
}
