package com.escaperfecto.repository;

import com.escaperfecto.db.Database;
import com.escaperfecto.model.Prize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPrizeRepository implements PrizeRepository {
    @Override
    public List<Prize> findAll() {
        List<Prize> prizes = new ArrayList<>();
        String sql = "SELECT id, nombre, valor, segundos_para_tomar, escape_seguro FROM premios";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                prizes.add(new Prize(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("valor"),
                        resultSet.getInt("segundos_para_tomar"),
                        resultSet.getInt("escape_seguro") == 1
                ));
            }
            return prizes;
        } catch (SQLException exception) {
            throw new IllegalStateException("No se pudieron cargar los premios.", exception);
        }
    }
}

