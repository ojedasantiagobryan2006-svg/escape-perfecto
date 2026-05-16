package com.escaperfecto.service;

import com.escaperfecto.model.GameResult;
import com.escaperfecto.model.Prize;
import com.escaperfecto.model.Question;
import com.escaperfecto.repository.GameRepository;
import com.escaperfecto.repository.PrizeRepository;
import com.escaperfecto.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameService {
    private final QuestionRepository questionRepository;
    private final PrizeRepository prizeRepository;
    private final GameRepository gameRepository;
    private List<Question> questions = new ArrayList<>();
    private List<Prize> prizes = new ArrayList<>();
    private int questionIndex;
    private int seconds;
    private int totalPrize;
    private boolean escaped;
    private String questionPlayer;
    private String cagePlayer;

    public GameService(QuestionRepository questionRepository, PrizeRepository prizeRepository, GameRepository gameRepository) {
        this.questionRepository = questionRepository;
        this.prizeRepository = prizeRepository;
        this.gameRepository = gameRepository;
    }

    public void startGame(String questionPlayer, String cagePlayer) {
        this.questionPlayer = questionPlayer;
        this.cagePlayer = cagePlayer;
        this.questions = questionRepository.findAll();
        this.prizes = prizeRepository.findAll();
        this.questionIndex = 0;
        this.seconds = 0;
        this.totalPrize = 0;
        this.escaped = false;
    }

    public Question getCurrentQuestion() {
        if (questionIndex >= questions.size()) {
            return null;
        }
        return questions.get(questionIndex);
    }

    public boolean answerCurrentQuestion(String answer) {
        Question current = getCurrentQuestion();
        if (current == null) {
            return false;
        }

        boolean correct = current.isCorrect(answer);
        if (correct) {
            seconds += current.getSeconds();
        }
        questionIndex++;
        return correct;
    }

    public boolean takePrize(Prize prize) {
        if (prize == null || seconds < prize.getSecondsToTake() || escaped) {
            return false;
        }

        seconds -= prize.getSecondsToTake();
        if (prize.isSafeEscape()) {
            escaped = true;
        } else {
            totalPrize += prize.getValue();
        }
        return true;
    }

    public GameResult finishGame(boolean escapedByDoor) {
        boolean finalEscape = escaped || escapedByDoor;
        int finalPrize = finalEscape ? totalPrize : 0;
        GameResult result = new GameResult(questionPlayer, cagePlayer, finalPrize, finalEscape, LocalDateTime.now());
        gameRepository.save(result);
        return result;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getTotalPrize() {
        return totalPrize;
    }

    public List<GameResult> getLastResults() {
        return gameRepository.findLastResults();
    }
}

