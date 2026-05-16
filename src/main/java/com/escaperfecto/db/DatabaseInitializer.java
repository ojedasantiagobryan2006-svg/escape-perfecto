package com.escaperfecto.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public void initialize() {
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS preguntas (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        texto TEXT NOT NULL,
                        opcion_a TEXT NOT NULL,
                        opcion_b TEXT NOT NULL,
                        opcion_c TEXT NOT NULL,
                        respuesta_correcta TEXT NOT NULL,
                        segundos INTEGER NOT NULL
                    )
                    """);

            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS premios (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre TEXT NOT NULL,
                        valor INTEGER NOT NULL,
                        segundos_para_tomar INTEGER NOT NULL,
                        escape_seguro INTEGER NOT NULL DEFAULT 0
                    )
                    """);

            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS partidas (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        jugador_preguntas TEXT NOT NULL,
                        jugador_jaula TEXT NOT NULL,
                        premio_total INTEGER NOT NULL,
                        escapo INTEGER NOT NULL,
                        fecha TEXT NOT NULL
                    )
                    """);

            seedQuestions(statement);
            seedPrizes(statement);
        } catch (SQLException exception) {
            throw new IllegalStateException("No se pudo inicializar la base de datos.", exception);
        }
    }

    private void seedQuestions(Statement statement) throws SQLException {
        statement.executeUpdate("""
                INSERT INTO preguntas (texto, opcion_a, opcion_b, opcion_c, respuesta_correcta, segundos)
                SELECT '¿Qué necesita el equipo para no perder los premios?', 'Salir antes de que cierre la puerta', 'Tomar solo premios pequeños', 'Responder sin mirar', 'A', 10
                WHERE NOT EXISTS (SELECT 1 FROM preguntas)
                """);
        statement.executeUpdate("""
                INSERT INTO preguntas (texto, opcion_a, opcion_b, opcion_c, respuesta_correcta, segundos)
                SELECT '¿Qué representa el escape seguro?', 'Un premio que permite salir sin arriesgar todo', 'Una pregunta extra', 'Una penalización', 'A', 12
                WHERE (SELECT COUNT(*) FROM preguntas) = 1
                """);
        statement.executeUpdate("""
                INSERT INTO preguntas (texto, opcion_a, opcion_b, opcion_c, respuesta_correcta, segundos)
                SELECT '¿Qué pasa si el tiempo llega a cero dentro de la jaula?', 'Se duplican los premios', 'El jugador queda atrapado y pierde la ronda', 'Gana automáticamente', 'B', 15
                WHERE (SELECT COUNT(*) FROM preguntas) = 2
                """);
        statement.executeUpdate("""
                INSERT INTO preguntas (texto, opcion_a, opcion_b, opcion_c, respuesta_correcta, segundos)
                SELECT '¿Qué habilidad ayuda más en una sala de escape?', 'Resolver acertijos bajo presión', 'Esperar sin decidir', 'Ignorar pistas', 'A', 8
                WHERE (SELECT COUNT(*) FROM preguntas) = 3
                """);
    }

    private void seedPrizes(Statement statement) throws SQLException {
        statement.executeUpdate("""
                INSERT INTO premios (nombre, valor, segundos_para_tomar, escape_seguro)
                SELECT 'Audífonos', 800, 5, 0
                WHERE NOT EXISTS (SELECT 1 FROM premios)
                """);
        statement.executeUpdate("""
                INSERT INTO premios (nombre, valor, segundos_para_tomar, escape_seguro)
                SELECT 'Tablet', 3500, 10, 0
                WHERE (SELECT COUNT(*) FROM premios) = 1
                """);
        statement.executeUpdate("""
                INSERT INTO premios (nombre, valor, segundos_para_tomar, escape_seguro)
                SELECT 'Bicicleta', 5000, 14, 0
                WHERE (SELECT COUNT(*) FROM premios) = 2
                """);
        statement.executeUpdate("""
                INSERT INTO premios (nombre, valor, segundos_para_tomar, escape_seguro)
                SELECT 'Escape seguro', 0, 6, 1
                WHERE (SELECT COUNT(*) FROM premios) = 3
                """);
    }
}

