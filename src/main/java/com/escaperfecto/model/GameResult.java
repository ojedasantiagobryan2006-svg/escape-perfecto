package com.escaperfecto.model;

import java.time.LocalDateTime;

public class GameResult {
    private final String questionPlayer;
    private final String cagePlayer;
    private final int totalPrize;
    private final boolean escaped;
    private final LocalDateTime date;

    public GameResult(String questionPlayer, String cagePlayer, int totalPrize, boolean escaped, LocalDateTime date) {
        this.questionPlayer = questionPlayer;
        this.cagePlayer = cagePlayer;
        this.totalPrize = totalPrize;
        this.escaped = escaped;
        this.date = date;
    }

    public String getQuestionPlayer() {
        return questionPlayer;
    }

    public String getCagePlayer() {
        return cagePlayer;
    }

    public int getTotalPrize() {
        return totalPrize;
    }

    public boolean isEscaped() {
        return escaped;
    }

    public LocalDateTime getDate() {
        return date;
    }
}

