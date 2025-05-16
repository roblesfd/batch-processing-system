package org.fernandodev.cli;

import org.fernandodev.core.pipelines.PipelineRegistry;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;

@Command(
        name = "convert",
        description = "Convierte archivos entre formatos compatibles"
)
public class ConvertCommand implements Runnable {

    @Option(
            names = {"-f", "--from"},
            description = "Extensión de origen",
            required = true
    )
    String from;

    @Option(
            names = {"-t", "--to"},
            description = "Extensión de destino",
            required = true
    )
    String to;

    @Option(
            names = {"-i", "--input"},
            description = "Archivo de entrada",
            required = true
    )
    File input;

    @Option(
            names = {"-o", "--output"},
            description = "Archivo de salida",
            required = true
    )
    File output;

    @Override
    public void run() {
        var pipelineOpt = PipelineRegistry.getPipeline(from, to);
        if(pipelineOpt.isPresent()){
            try {
                pipelineOpt.get().process(input, output);
                System.out.printf("✅ Archivo convertido: %s → %s%n", input.getName(), output.getName());
            } catch (Exception e) {
                System.err.println("❌ Error durante la conversión: " + e.getMessage());
            }
        }else {
            System.err.printf("❌ Conversión no soportada: %s → %s%n", from, to);
        }
    }
}
