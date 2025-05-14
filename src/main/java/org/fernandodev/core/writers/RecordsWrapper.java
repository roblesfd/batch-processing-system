package org.fernandodev.core.writers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class RecordsWrapper {
    @JsonProperty("record")
    public List<Map<String, Object>> record;

    public RecordsWrapper() {}

    public RecordsWrapper(List<Map<String, Object>> record) {
        this.record = record;
    }
}
