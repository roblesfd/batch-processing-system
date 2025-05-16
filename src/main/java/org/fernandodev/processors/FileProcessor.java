package org.fernandodev.processors;

import org.fernandodev.model.ProcessingResult;

import java.io.File;

public interface FileProcessor {
    ProcessingResult process(File file);
}
