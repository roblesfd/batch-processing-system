package org.fernandodev.core.writers;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;
import java.util.Map;

public class RecordsWrapper {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "record")
    public List<Map<String, Object>> records;

    public RecordsWrapper(List<Map<String, Object>> records) {
        this.records = records;
    }

    // Jackson necesita un constructor por defecto si deserializas (opcional en este caso)
    public RecordsWrapper() {}
}

