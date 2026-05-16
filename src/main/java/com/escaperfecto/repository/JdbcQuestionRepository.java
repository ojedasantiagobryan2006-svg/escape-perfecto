package com.escaperfecto.repository;

import com.escaperfecto.db.Database;
import com.escaperfecto.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcQuestionRepository implements QuestionRepository {
    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT id, texto, opcion_a, opcion_b, opcion_c, respuesta_correcta, segundos FROM preguntas";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                questions.add(new Question(
                        resultSet.getInt("id"),
                        resultSet.getString("texto"),
                        resultSet.getString("opcion_a"),
                        resultSet.getString("opcion_b"),
                        resultSet.getString("opcion_c"),
                        resultSet.getString("respuesta_correcta"),
                        resultSet.getInt("segundos")
                ));
            }
            return questions;
        } catch (SQLException exception) {
            throw new IllegalStateException("No se pudieron cargar las preguntas.", exception);
        }
    }
}

