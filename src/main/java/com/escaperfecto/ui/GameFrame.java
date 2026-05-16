package com.escaperfecto.ui;

import com.escaperfecto.model.GameResult;
import com.escaperfecto.model.Prize;
import com.escaperfecto.model.Question;
import com.escaperfecto.service.GameService;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

public class GameFrame extends JFrame {
    private final GameService gameService;
    private final JTextField questionPlayerField = new JTextField("Jugador preguntas");
    private final JTextField cagePlayerField = new JTextField("Jugador jaula");
    private final JLabel timerLabel = new JLabel("Tiempo: 0s");
    private final JLabel totalLabel = new JLabel("Premios: $0");
    private final JTextArea questionArea = new JTextArea();
    private final JRadioButton optionA = new JRadioButton();
    private final JRadioButton optionB = new JRadioButton();
    private final JRadioButton optionC = new JRadioButton();
    private final ButtonGroup answerGroup = new ButtonGroup();
    private final DefaultListModel<Prize> prizeListModel = new DefaultListModel<>();
    private final JList<Prize> prizeList = new JList<>(prizeListModel);
    private final JTextArea historyArea = new JTextArea();
    private boolean gameStarted;
    private boolean gameFinished;

    public GameFrame(GameService gameService) {
        this.gameService = gameService;
        configureWindow();
        buildLayout();
        refreshHistory();
    }

    private void configureWindow() {
        setTitle("Escape Perfecto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(940, 620);
        setMinimumSize(new Dimension(820, 540));
        setLocationRelativeTo(null);
    }

    private void buildLayout() {
        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        root.setBackground(new Color(245, 247, 250));

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildGamePanel(), BorderLayout.CENTER);
        root.add(buildPrizePanel(), BorderLayout.EAST);

        setContentPane(root);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new GridLayout(2, 4, 10, 8));
        header.setOpaque(false);

        JButton startButton = new JButton("Nueva partida");
        startButton.addActionListener(event -> startGame());

        timerLabel.setFont(timerLabel.getFont().deriveFont(Font.BOLD, 18f));
        totalLabel.setFont(totalLabel.getFont().deriveFont(Font.BOLD, 18f));

        header.add(new JLabel("Responde:"));
        header.add(questionPlayerField);
        header.add(new JLabel("Entra a la jaula:"));
        header.add(cagePlayerField);
        header.add(startButton);
        header.add(timerLabel);
        header.add(totalLabel);
        header.add(new JLabel("Base de datos: SQLite"));

        return header;
    }

    private JPanel buildGamePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);

        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(questionArea.getFont().deriveFont(17f));
        questionArea.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        questionArea.setText("Presiona Nueva partida para comenzar.");

        JPanel options = new JPanel(new GridLayout(3, 1, 6, 6));
        options.setOpaque(false);
        answerGroup.add(optionA);
        answerGroup.add(optionB);
        answerGroup.add(optionC);
        options.add(optionA);
        options.add(optionB);
        options.add(optionC);

        JButton answerButton = new JButton("Responder");
        answerButton.addActionListener(event -> answerQuestion());

        JButton escapeButton = new JButton("Salir de la jaula");
        escapeButton.addActionListener(event -> finishGame(true));

        JPanel actions = new JPanel(new GridLayout(1, 2, 8, 8));
        actions.setOpaque(false);
        actions.add(answerButton);
        actions.add(escapeButton);

        historyArea.setEditable(false);
        historyArea.setRows(7);

        panel.add(new JScrollPane(questionArea), BorderLayout.NORTH);
        panel.add(options, BorderLayout.CENTER);
        panel.add(actions, BorderLayout.SOUTH);
        panel.add(new JScrollPane(historyArea), BorderLayout.EAST);
        return panel;
    }

    private JPanel buildPrizePanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setPreferredSize(new Dimension(300, 0));
        panel.setOpaque(false);

        JLabel title = new JLabel("Premios en la jaula");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));

        JButton takeButton = new JButton("Tomar premio");
        takeButton.addActionListener(event -> takeSelectedPrize());

        JButton trappedButton = new JButton("Se cerró la puerta");
        trappedButton.addActionListener(event -> finishGame(false));

        JPanel buttons = new JPanel(new GridLayout(2, 1, 8, 8));
        buttons.setOpaque(false);
        buttons.add(takeButton);
        buttons.add(trappedButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(prizeList), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        return panel;
    }

    private void startGame() {
        String questionPlayer = questionPlayerField.getText().trim();
        String cagePlayer = cagePlayerField.getText().trim();
        if (questionPlayer.isEmpty() || cagePlayer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escribe los nombres de los dos participantes.");
            return;
        }

        gameService.startGame(questionPlayer, cagePlayer);
        gameStarted = true;
        gameFinished = false;
        loadPrizes();
        showCurrentQuestion();
        refreshScore();
    }

    private void answerQuestion() {
        if (!canPlay()) {
            return;
        }

        String selectedAnswer = getSelectedAnswer();
        if (selectedAnswer == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una respuesta.");
            return;
        }

        boolean correct = gameService.answerCurrentQuestion(selectedAnswer);
        JOptionPane.showMessageDialog(this, correct ? "Correcto, ganaron segundos." : "Incorrecto, no ganan tiempo.");
        showCurrentQuestion();
        refreshScore();
    }

    private void takeSelectedPrize() {
        if (!canPlay()) {
            return;
        }

        Prize selectedPrize = prizeList.getSelectedValue();
        if (selectedPrize == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un premio.");
            return;
        }

        boolean taken = gameService.takePrize(selectedPrize);
        if (!taken) {
            JOptionPane.showMessageDialog(this, "No hay tiempo suficiente o la partida ya terminó.");
            return;
        }

        prizeListModel.removeElement(selectedPrize);
        if (selectedPrize.isSafeEscape()) {
            JOptionPane.showMessageDialog(this, "Tomaron el escape seguro. Ya pueden conservar los premios.");
        }
        refreshScore();
    }

    private void finishGame(boolean escaped) {
        if (!canPlay()) {
            return;
        }

        gameFinished = true;
        GameResult result = gameService.finishGame(escaped);
        String message = result.isEscaped()
                ? "Escaparon con $" + result.getTotalPrize()
                : "Quedaron atrapados. Premio final: $0";
        JOptionPane.showMessageDialog(this, message);
        refreshHistory();
        refreshScore();
    }

    private boolean canPlay() {
        if (!gameStarted) {
            JOptionPane.showMessageDialog(this, "Primero inicia una nueva partida.");
            return false;
        }
        if (gameFinished) {
            JOptionPane.showMessageDialog(this, "La partida ya terminó. Inicia una nueva partida.");
            return false;
        }
        return true;
    }

    private void showCurrentQuestion() {
        Question question = gameService.getCurrentQuestion();
        answerGroup.clearSelection();
        if (question == null) {
            questionArea.setText("Ya no hay más preguntas. Usen el tiempo ganado para tomar premios o salir.");
            optionA.setText("");
            optionB.setText("");
            optionC.setText("");
            return;
        }

        questionArea.setText(question.getText() + "\n\nTiempo que otorga: " + question.getSeconds() + " segundos");
        optionA.setText("A) " + question.getOptionA());
        optionB.setText("B) " + question.getOptionB());
        optionC.setText("C) " + question.getOptionC());
    }

    private String getSelectedAnswer() {
        if (optionA.isSelected()) {
            return "A";
        }
        if (optionB.isSelected()) {
            return "B";
        }
        if (optionC.isSelected()) {
            return "C";
        }
        return null;
    }

    private void loadPrizes() {
        prizeListModel.clear();
        for (Prize prize : gameService.getPrizes()) {
            prizeListModel.addElement(prize);
        }
    }

    private void refreshScore() {
        timerLabel.setText("Tiempo: " + gameService.getSeconds() + "s");
        totalLabel.setText("Premios: $" + gameService.getTotalPrize());
    }

    private void refreshHistory() {
        List<GameResult> results = gameService.getLastResults();
        StringBuilder builder = new StringBuilder("Últimas partidas\n\n");
        for (GameResult result : results) {
            builder.append(result.getCagePlayer())
                    .append(result.isEscaped() ? " escapó con $" : " quedó atrapado con $")
                    .append(result.getTotalPrize())
                    .append("\n");
        }
        historyArea.setText(builder.toString());
    }
}
