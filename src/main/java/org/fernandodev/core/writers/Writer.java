package org.fernandodev.core.writers;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

public interface Writer {
    void write(JsonNode data, File outputFile) throws IOException;
}