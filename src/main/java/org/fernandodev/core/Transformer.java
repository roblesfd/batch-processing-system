package org.fernandodev.core;

import com.fasterxml.jackson.databind.JsonNode;
import org.fernandodev.core.parsers.Parser;
import org.fernandodev.core.writers.Writer;

import java.io.File;

public class Transformer<T> {
    private final Parser parser;
    private final Writer writer;

    public Transformer(Parser parser, Writer writer) {
        this.parser =  parser;
        this.writer = writer;
    }

    public void transform(File inputFile, File outputFile) throws Exception{
        JsonNode data = parser.parse(inputFile);
        writer.write(data, outputFile);
    }

}
