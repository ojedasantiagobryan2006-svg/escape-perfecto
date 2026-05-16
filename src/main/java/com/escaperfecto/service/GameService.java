package com.escaperfecto.service;

import com.escaperfecto.model.GameResult;
import com.escaperfecto.model.Prize;
import com.escaperfecto.model.Question;
import com.escaperfecto.repository.GameRepository;
import com.escaperfecto.repository.PrizeRepository;
import com.escaperfecto.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GameService {
    private final QuestionRepository questionRepository;
    private final PrizeRepository prizeRepository;
    private final GameRepository gameRepository;
    private List<Question> questions = new ArrayList<>();
    private List<Question> allQuestions = new ArrayList<>();
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
        startGame(questionPlayer, cagePlayer, "Todas");
    }

    public void startGame(String questionPlayer, String cagePlayer, String category) {
        this.questionPlayer = questionPlayer;
        this.cagePlayer = cagePlayer;
        this.allQuestions = questionRepository.findAll();
        if (category == null || category.equals("Todas")) {
            this.questions = new ArrayList<>(allQuestions);
        } else {
            this.questions = allQuestions.stream()
                    .filter(question -> question.getCategory().equals(category))
                    .toList();
        }
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

    public Question changeQuestion() {
        if (questionIndex < questions.size()) {
            questionIndex++;
        }
        return getCurrentQuestion();
    }

    public Question changeCategory(String category) {
        if (category == null || category.equals("Todas")) {
            this.questions = new ArrayList<>(allQuestions);
        } else {
            this.questions = allQuestions.stream()
                    .filter(question -> question.getCategory().equals(category))
                    .toList();
        }
        this.questionIndex = 0;
        return getCurrentQuestion();
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

    public List<String> getCategories() {
        Set<String> categories = new LinkedHashSet<>();
        categories.add("Todas");
        for (Question question : questionRepository.findAll()) {
            categories.add(question.getCategory());
        }
        return new ArrayList<>(categories);
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
