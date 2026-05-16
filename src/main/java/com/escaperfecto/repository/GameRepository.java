package com.escaperfecto.repository;

import com.escaperfecto.model.GameResult;

import java.util.List;

public interface GameRepository {
    void save(GameResult result);

    List<GameResult> findLastResults();
}

