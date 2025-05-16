package org.fernandodev.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = getEnv("DATABASE_URL");
    private static final String USER = getEnv("POSTGRES_USER");
    private static final String PASSWORD = getEnv("POSTGRES_PASSWORD");

    public static Connection getConnection() throws SQLException {
        if (URL == null) {
            throw new SQLException("DATABASE_URL no está definida en las variables de entorno");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static String getEnv(String key) {
        String sys = System.getenv(key);
        return (sys != null) ? sys : dotenv.get(key);
    }
}
