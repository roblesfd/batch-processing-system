package org.fernandodev;

import org.fernandodev.core.observers.DirectoryWatcher;
import org.fernandodev.core.pipelines.PipelineRegistry;
import org.fernandodev.core.registry.TransformerRegistry;

import java.io.File;
import java.nio.file.Paths;
/*
    TODO:
    -corregir conversion incorrecta de archivos origen json
 */

public class Main {
    public static void main(String[] args) throws Exception {
        String inputPath = Paths.get("src/main/resources").toAbsolutePath().toString();
        String outputPath = Paths.get("output").toAbsolutePath().toString();
        File inputFile = new File(inputPath + "/customers-2000000.csv");
        File outputFile = new File(outputPath + "/from-csv-to-json.json");
        String from = "csv";
        String to = "json";
        DirectoryWatcher watcher = new DirectoryWatcher(inputPath, outputPath);
        watcher.start();

        System.out.println(PipelineRegistry.listPipelines());

//        PipelineRegistry
//                .getPipeline(from, to)
//                .ifPresentOrElse(
//                    step -> {
//                        try {
//                            step.process(inputFile, outputFile);
//                            System.out.println("✅ Conversión completada de " + from + " a " + to);
//                        }catch(Exception e){
//                            System.err.println("❌ Error al convertir: " + e.getMessage());
//                        }
//                    }, () -> System.err.println("❌ No hay pipeline para convertir de " + from + " a " + to)
//                );


        //Conversion de tipos de archivo
        //csv - json OK
//        TransformerRegistry.transform(
//                "csv",
//                "json",
//                new File(inputPath + "/customers-2000000.csv"),
//                new File(outputPath + "/from-csv-to-json.json")
//        );
    }
}