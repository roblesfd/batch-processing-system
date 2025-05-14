package org.fernandodev.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = System.getenv("DATABASE_URL");
    private static final String USER = System.getenv("POSTGRES_USER");
    private static final String PASSWORD = System.getenv("POSTGRES_PASSWORD");

    public static Connection getConnection() throws SQLException {
        if (URL == null) {
            throw new SQLException("DATABASE_URL no está definida en las variables de entorno");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
