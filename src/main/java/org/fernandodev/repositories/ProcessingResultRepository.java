package org.fernandodev.repositories;

import org.fernandodev.utils.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProcessingResultRepository {
    private final Connection connection;

    public ProcessingResultRepository() throws SQLException {
        this.connection = DatabaseConfig.getConnection();
    }

    public void save(String filename, String status, String message, LocalDateTime processedAt) {
        String sql = "INSERT INTO processing_results(filename, status, message, processed_at) VALUES(?,?,?,?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, filename);
            stmt.setString(2, status);
            stmt.setString(3, message);
            stmt.setTimestamp(4, Timestamp.valueOf(processedAt));
            stmt.executeUpdate();
        }catch(SQLException e) {
            System.err.println("❌ Error al guardar el resultado en la base de datos: " + e.getMessage());
        }
    }
}