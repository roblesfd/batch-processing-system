package org.fernandodev.core;

import org.fernandodev.model.ProcessingResult;

import java.io.File;

public interface FileProcessor {
    ProcessingResult process(File file);
}
