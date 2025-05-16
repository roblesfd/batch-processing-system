package org.fernandodev.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

import java.util.Scanner;

/*
    TODO
    -corregir que este aceptando un archivo de ext en --output diferente al ingresado en --to
    -corregir que este aceptando un archivo de ext en --input diferente al ingresado en --from
 */

@Command(
        name = "batch-cli",
        description="Procesador de archivos por lotes cli",
        subcommands= {
                ConvertCommand.class,
                HelpCommand.class,
                ExitCommand.class,
                WatchDirectoryCommand.class,
                ProcessFilesCommand.class
        }
)
public class BatchCLI implements Runnable{
    @Override
    public void run() {
        System.out.println("Escribe un comando. Usa 'help' para asistencia.");
    }

    public static void main(String[] args) {
        System.out.println("\uD83C\uDF00 Bienvenido a BatchProcessor CLI �\uDF00");
        System.out.println("Escribe 'help' para ver los comandos disponibles.");

        Scanner scanner = new Scanner(System.in);
        CommandLine cmd = new CommandLine(new BatchCLI());

        while(true) {
            System.out.print("> ");
            String line = scanner.nextLine();

            // Parsear y ejecutar el comando (incluido help)
            String[] lineArgs = line.trim().split("\\s+");
            if (lineArgs.length == 0 || lineArgs[0].isEmpty()) continue;

            try {
                int exitCode = cmd.execute(lineArgs);
                if (exitCode != 0) {
                    System.out.println("Error: código de salida " + exitCode);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
