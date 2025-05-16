package org.fernandodev.cli;

import org.fernandodev.core.BatchRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.sql.SQLException;

@Command(
        name = "process",
        description = "Procesa y almacena todos los archivos de un directorio"
)
public class ProcessFilesCommand implements Runnable{
    @Option(
     names = {"-d", "--dir"},
     description = "Directorio de archivos a procesar",
     required = true
    )
    String inputDirectory;

    @Override
    public void run() {
        try {
            new BatchRunner(inputDirectory, inputDirectory).run();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
