package org.fernandodev.core.parsers;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

public interface Parser {
    JsonNode parse(File file) throws IOException;
}
