package org.fernandodev;

import org.fernandodev.core.observers.DirectoryWatcher;
import org.fernandodev.core.registry.TransformerRegistry;

import java.io.File;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputPath = Paths.get("src/main/resources").toAbsolutePath().toString();
        String outputPath = Paths.get("output").toAbsolutePath().toString();
        DirectoryWatcher watcher = new DirectoryWatcher(inputPath, outputPath);
        watcher.start();

        //Conversion de tipos de archivo
        //csv - json OK
//        TransformerRegistry.transform(
//                "csv",
//                "json",
//                new File(inputPath + "/customers-2000000.csv"),
//                new File(outputPath + "/from-csv-to-json.json")
//        );

        //csv - xml OK
//        TransformerRegistry.transform(
//                "csv",
//                "xml",
//                new File(inputPath + "/customers-2000000.csv"),
//                new File(outputPath + "/from-csv-to-xml.xml")
//        );

        //xml - csv OK
//        TransformerRegistry.transform(
//                "xml",
//                "csv",
//                new File(inputPath + "/large-dataset.xml"),
//                new File(outputPath + "/from-xml-to-csv.csv")
//        );

        //xml - json OK
//        TransformerRegistry.transform(
//                "xml",
//                "json",
//                new File(inputPath + "/large-dataset.xml"),
//                new File(outputPath + "/from-xml-to-json.json")
//        );

        // json - csv X
//        TransformerRegistry.transform(
//                "json",
//                "csv",
//                new File(inputPath + "/flights-1m.json"),
//                new File(outputPath + "/from-json-to-csv.csv")
//        );

        //json - xml X
//        TransformerRegistry.transform(
//                "json",
//                "xml",
//                new File(inputPath + "/flights-1m.json"),
//                new File(outputPath + "/from-json-to-xml.xml")
//        );
    }
}