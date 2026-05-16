package com.escaperfecto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {
    private static final String HOST_URL = getEnv("DB_HOST_URL", "jdbc:mysql://localhost:3306/");
    private static final String DATABASE_NAME = getEnv("DB_NAME", "escape_perfecto");
    private static final String PARAMS = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = getEnv("DB_USER", "root");
    private static final String PASSWORD = getEnv("DB_PASSWORD", "");

    private Database() {
    }

    public static Connection getServerConnection() throws SQLException {
        return DriverManager.getConnection(HOST_URL + PARAMS, USER, PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(HOST_URL + DATABASE_NAME + PARAMS, USER, PASSWORD);
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    private static String getEnv(String name, String defaultValue) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return value;
    }
}
