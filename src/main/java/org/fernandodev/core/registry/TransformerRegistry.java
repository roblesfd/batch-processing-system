package org.fernandodev.core.registry;

import org.fernandodev.core.Transformer;
import org.fernandodev.core.parsers.Parser;
import org.fernandodev.core.writers.Writer;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public class TransformerRegistry {
    public static void transform(String inputExt, String outputExt, File inputFile, File outputFile) throws Exception {
        Optional<Parser> parser = ParserRegistry.getParser(inputExt);
        Optional<Writer> writer = WriterRegistry.getWriter(outputExt);

        if(parser.isEmpty()) {
                throw new IllegalArgumentException("No parser para extensión: " + inputExt);
            }

        if(writer.isEmpty()) {
            throw new IllegalArgumentException("No writer para extensión: " + outputExt);
        }

        Transformer<Map<String, String>> transformer = new Transformer<>(parser.get(), writer.get());
        transformer.transform(inputFile, outputFile);
    }
}
