package org.fernandodev.cli;

import picocli.CommandLine.Command;

@Command(
        name = "exit",
        description = "Sale de la aplicación"
)
public class ExitCommand implements Runnable{
    @Override
    public void run() {
        System.out.println("\uD83D\uDC4B ¡Hasta luego!");
        System.exit(0);
    }
}
