package org.fernandodev.core.registry;

import org.fernandodev.core.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class PluginRegistry {
    private static final Map<String, Plugin> plugins = new HashMap<>();

    public static Optional<Plugin> get(String name) {
        return Optional.ofNullable(plugins.get(name));
    }

    public static Set<String> list() {
        return plugins.keySet();
    }
}
