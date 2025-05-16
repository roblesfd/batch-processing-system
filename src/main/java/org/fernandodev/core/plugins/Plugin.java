package org.fernandodev.core.plugins;

import com.fasterxml.jackson.databind.JsonNode;

public interface Plugin {
    JsonNode apply(JsonNode input);
}
