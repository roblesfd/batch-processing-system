package org.fernandodev.cli;

import org.fernandodev.core.observers.DirectoryWatcher;
import org.fernandodev.core.pipelines.PipelineRegistry;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;


@Command(
        name = "watch",
        description = "Detecta si hay archivos nuevos en un directorio para procesarlos"
)
public class WatchDirectoryCommand implements Runnable{
    @Option(
            names = {"-d", "--dir"},
            description = "Directorio a observar",
            required = true
    )
    String inputDirectory;

    @Override
    public void run(){
        try {
            DirectoryWatcher watcher = new DirectoryWatcher(inputDirectory,inputDirectory);
            watcher.start();
        } catch (IOException | InterruptedException e) {
            System.err.println("❌ Error en le proceso watch " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
