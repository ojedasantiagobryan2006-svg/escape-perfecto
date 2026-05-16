package com.escaperfecto.repository;

import com.escaperfecto.db.Database;
import com.escaperfecto.model.GameResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcGameRepository implements GameRepository {
    @Override
    public void save(GameResult result) {
        String sql = """
                INSERT INTO partidas (jugador_preguntas, jugador_jaula, premio_total, escapo, fecha)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, result.getQuestionPlayer());
            statement.setString(2, result.getCagePlayer());
            statement.setInt(3, result.getTotalPrize());
            statement.setInt(4, result.isEscaped() ? 1 : 0);
            statement.setString(5, result.getDate().toString());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new IllegalStateException("No se pudo guardar la partida.", exception);
        }
    }

    @Override
    public List<GameResult> findLastResults() {
        List<GameResult> results = new ArrayList<>();
        String sql = """
                SELECT jugador_preguntas, jugador_jaula, premio_total, escapo, fecha
                FROM partidas
                ORDER BY id DESC
                LIMIT 8
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                results.add(new GameResult(
                        resultSet.getString("jugador_preguntas"),
                        resultSet.getString("jugador_jaula"),
                        resultSet.getInt("premio_total"),
                        resultSet.getInt("escapo") == 1,
                        LocalDateTime.parse(resultSet.getString("fecha"))
                ));
            }
            return results;
        } catch (SQLException exception) {
            throw new IllegalStateException("No se pudo consultar el historial.", exception);
        }
    }
}

