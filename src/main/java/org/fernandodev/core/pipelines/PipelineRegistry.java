package org.fernandodev.core.pipelines;

import org.fernandodev.core.parsers.Parser;
import org.fernandodev.core.registry.ParserRegistry;
import org.fernandodev.core.registry.WriterRegistry;
import org.fernandodev.core.writers.Writer;

import java.io.File;
import java.util.*;

public class PipelineRegistry {
    private static final Map<String, ProcessingStep> pipelines = new HashMap<>();

    static {
        Set<String> fromExtensions = ParserRegistry.getSupportedExtensions();
        Set<String> toExtensions = WriterRegistry.getSupportedExtensions();

        for(String from : fromExtensions) {
            for(String to : toExtensions) {
                if(!from.equalsIgnoreCase(to)) {
                    Optional<Parser> parserOpt = ParserRegistry.getParser(from);
                    Optional<Writer> writerOpt = WriterRegistry.getWriter(to);

                    if(parserOpt.isPresent() && writerOpt.isPresent()) {
                        ProcessingStep step = (File input, File output) -> {
                            var data = parserOpt.get().parse(input);
                            writerOpt.get().write(data, output);
                        };

                        String key = from + "-" + to;
                        pipelines.put(key, step);
                    }
                }
            }
        }
    }

    public static Optional<ProcessingStep> getPipeline(String from, String to) {
        return Optional.ofNullable(pipelines.get(from.toLowerCase() + "-" + to.toLowerCase()));
    }

    public static boolean isSupported(String from, String to) {
        return pipelines.containsKey(from.toLowerCase() + "-" + to.toLowerCase());
    }

    public static String listPipelines(){
        StringBuilder sb = new StringBuilder("Pipelines registrados: \n");
        pipelines.keySet().forEach(key -> sb.append(" - ").append(key).append("\n"));
        return sb.toString();
    }
}
