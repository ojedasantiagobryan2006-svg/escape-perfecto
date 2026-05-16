package com.escaperfecto.ui;

import com.escaperfecto.model.GameResult;
import com.escaperfecto.model.Prize;
import com.escaperfecto.model.Question;
import com.escaperfecto.service.GameService;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class GameFrame extends javax.swing.JFrame {
    private final GameService gameService;
    private final DefaultListModel<Prize> prizeListModel = new DefaultListModel<>();
    private boolean gameStarted;
    private boolean gameFinished;

    public GameFrame(GameService gameService) {
        this.gameService = gameService;
        initComponents();
        prizeList.setModel(prizeListModel);
        configureWindow();
        refreshHistory();
    }

    private void configureWindow() {
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 247, 250));
        timerLabel.setFont(timerLabel.getFont().deriveFont(Font.BOLD, 18f));
        totalLabel.setFont(totalLabel.getFont().deriveFont(Font.BOLD, 18f));
        questionArea.setText("Presiona Nueva partida para comenzar.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * NetBeans can edit these components from the Design tab.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        answerGroup = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        questionPlayerLabel = new javax.swing.JLabel();
        questionPlayerField = new javax.swing.JTextField();
        cagePlayerLabel = new javax.swing.JLabel();
        cagePlayerField = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        timerLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        databaseLabel = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();
        questionScrollPane = new javax.swing.JScrollPane();
        questionArea = new javax.swing.JTextArea();
        optionsPanel = new javax.swing.JPanel();
        optionA = new javax.swing.JRadioButton();
        optionB = new javax.swing.JRadioButton();
        optionC = new javax.swing.JRadioButton();
        actionsPanel = new javax.swing.JPanel();
        answerButton = new javax.swing.JButton();
        escapeButton = new javax.swing.JButton();
        historyScrollPane = new javax.swing.JScrollPane();
        historyArea = new javax.swing.JTextArea();
        prizePanel = new javax.swing.JPanel();
        prizeTitleLabel = new javax.swing.JLabel();
        prizeScrollPane = new javax.swing.JScrollPane();
        prizeList = new javax.swing.JList<>();
        prizeActionsPanel = new javax.swing.JPanel();
        takePrizeButton = new javax.swing.JButton();
        trappedButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Escape Perfecto");
        setMinimumSize(new Dimension(820, 540));
        setPreferredSize(new Dimension(940, 620));

        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 14, 14, 14));
        mainPanel.setLayout(new java.awt.BorderLayout(12, 12));

        headerPanel.setOpaque(false);
        headerPanel.setLayout(new java.awt.GridLayout(2, 4, 10, 8));

        questionPlayerLabel.setText("Responde:");
        headerPanel.add(questionPlayerLabel);

        questionPlayerField.setText("Jugador preguntas");
        headerPanel.add(questionPlayerField);

        cagePlayerLabel.setText("Entra a la jaula:");
        headerPanel.add(cagePlayerLabel);

        cagePlayerField.setText("Jugador jaula");
        headerPanel.add(cagePlayerField);

        startButton.setText("Nueva partida");
        startButton.addActionListener(evt -> startButtonActionPerformed(evt));
        headerPanel.add(startButton);

        timerLabel.setText("Tiempo: 0s");
        headerPanel.add(timerLabel);

        totalLabel.setText("Premios: $0");
        headerPanel.add(totalLabel);

        databaseLabel.setText("Base de datos: SQLite");
        headerPanel.add(databaseLabel);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        centerPanel.setOpaque(false);
        centerPanel.setLayout(new java.awt.BorderLayout(10, 10));

        questionArea.setEditable(false);
        questionArea.setColumns(20);
        questionArea.setFont(questionArea.getFont().deriveFont(17f));
        questionArea.setLineWrap(true);
        questionArea.setRows(5);
        questionArea.setWrapStyleWord(true);
        questionArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        questionScrollPane.setViewportView(questionArea);

        centerPanel.add(questionScrollPane, java.awt.BorderLayout.NORTH);

        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new java.awt.GridLayout(3, 1, 6, 6));

        answerGroup.add(optionA);
        optionsPanel.add(optionA);

        answerGroup.add(optionB);
        optionsPanel.add(optionB);

        answerGroup.add(optionC);
        optionsPanel.add(optionC);

        centerPanel.add(optionsPanel, java.awt.BorderLayout.CENTER);

        actionsPanel.setOpaque(false);
        actionsPanel.setLayout(new java.awt.GridLayout(1, 2, 8, 8));

        answerButton.setText("Responder");
        answerButton.addActionListener(evt -> answerButtonActionPerformed(evt));
        actionsPanel.add(answerButton);

        escapeButton.setText("Salir de la jaula");
        escapeButton.addActionListener(evt -> escapeButtonActionPerformed(evt));
        actionsPanel.add(escapeButton);

        centerPanel.add(actionsPanel, java.awt.BorderLayout.SOUTH);

        historyArea.setEditable(false);
        historyArea.setColumns(20);
        historyArea.setRows(7);
        historyScrollPane.setViewportView(historyArea);

        centerPanel.add(historyScrollPane, java.awt.BorderLayout.EAST);

        mainPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        prizePanel.setOpaque(false);
        prizePanel.setPreferredSize(new Dimension(300, 0));
        prizePanel.setLayout(new java.awt.BorderLayout(8, 8));

        prizeTitleLabel.setFont(prizeTitleLabel.getFont().deriveFont(Font.BOLD, 16f));
        prizeTitleLabel.setText("Premios en la jaula");
        prizePanel.add(prizeTitleLabel, java.awt.BorderLayout.NORTH);

        prizeScrollPane.setViewportView(prizeList);
        prizePanel.add(prizeScrollPane, java.awt.BorderLayout.CENTER);

        prizeActionsPanel.setOpaque(false);
        prizeActionsPanel.setLayout(new java.awt.GridLayout(2, 1, 8, 8));

        takePrizeButton.setText("Tomar premio");
        takePrizeButton.addActionListener(evt -> takePrizeButtonActionPerformed(evt));
        prizeActionsPanel.add(takePrizeButton);

        trappedButton.setText("Se cerro la puerta");
        trappedButton.addActionListener(evt -> trappedButtonActionPerformed(evt));
        prizeActionsPanel.add(trappedButton);

        prizePanel.add(prizeActionsPanel, java.awt.BorderLayout.SOUTH);

        mainPanel.add(prizePanel, java.awt.BorderLayout.EAST);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
        startGame();
    }

    private void answerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        answerQuestion();
    }

    private void escapeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        finishGame(true);
    }

    private void takePrizeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        takeSelectedPrize();
    }

    private void trappedButtonActionPerformed(java.awt.event.ActionEvent evt) {
        finishGame(false);
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

        Object selectedValue = prizeList.getSelectedValue();
        if (!(selectedValue instanceof Prize selectedPrize)) {
            JOptionPane.showMessageDialog(this, "Selecciona un premio.");
            return;
        }

        boolean taken = gameService.takePrize(selectedPrize);
        if (!taken) {
            JOptionPane.showMessageDialog(this, "No hay tiempo suficiente o la partida ya termino.");
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
            JOptionPane.showMessageDialog(this, "La partida ya termino. Inicia una nueva partida.");
            return false;
        }
        return true;
    }

    private void showCurrentQuestion() {
        Question question = gameService.getCurrentQuestion();
        answerGroup.clearSelection();
        if (question == null) {
            questionArea.setText("Ya no hay mas preguntas. Usen el tiempo ganado para tomar premios o salir.");
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
        StringBuilder builder = new StringBuilder("Ultimas partidas\n\n");
        for (GameResult result : results) {
            builder.append(result.getCagePlayer())
                    .append(result.isEscaped() ? " escapo con $" : " quedo atrapado con $")
                    .append(result.getTotalPrize())
                    .append("\n");
        }
        historyArea.setText(builder.toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JButton answerButton;
    private javax.swing.ButtonGroup answerGroup;
    private javax.swing.JLabel cagePlayerLabel;
    private javax.swing.JTextField cagePlayerField;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel databaseLabel;
    private javax.swing.JButton escapeButton;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JTextArea historyArea;
    private javax.swing.JScrollPane historyScrollPane;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButton optionA;
    private javax.swing.JRadioButton optionB;
    private javax.swing.JRadioButton optionC;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JList<Prize> prizeList;
    private javax.swing.JPanel prizeActionsPanel;
    private javax.swing.JPanel prizePanel;
    private javax.swing.JScrollPane prizeScrollPane;
    private javax.swing.JLabel prizeTitleLabel;
    private javax.swing.JLabel questionPlayerLabel;
    private javax.swing.JTextField questionPlayerField;
    private javax.swing.JTextArea questionArea;
    private javax.swing.JScrollPane questionScrollPane;
    private javax.swing.JButton startButton;
    private javax.swing.JButton takePrizeButton;
    private javax.swing.JLabel timerLabel;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JButton trappedButton;
    // End of variables declaration//GEN-END:variables
}
