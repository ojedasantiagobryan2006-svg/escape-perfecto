package com.escaperfecto;

import com.escaperfecto.db.DatabaseInitializer;
import com.escaperfecto.repository.JdbcGameRepository;
import com.escaperfecto.repository.JdbcPrizeRepository;
import com.escaperfecto.repository.JdbcQuestionRepository;
import com.escaperfecto.service.GameService;
import com.escaperfecto.ui.GameFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatabaseInitializer initializer = new DatabaseInitializer();
            initializer.initialize();

            GameService gameService = new GameService(
                    new JdbcQuestionRepository(),
                    new JdbcPrizeRepository(),
                    new JdbcGameRepository()
            );

            GameFrame frame = new GameFrame(gameService);
            frame.setVisible(true);
        });
    }
}

