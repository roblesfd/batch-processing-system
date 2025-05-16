package org.fernandodev.core.pipelines;

import java.io.File;

@FunctionalInterface
public interface ProcessingStep {
    void process(File input, File output) throws Exception;

    default ProcessingStep andThen(ProcessingStep next) {
        return ((input, output) -> {
           this.process(input, output);
           next.process(input, output);
        });
    }
}
