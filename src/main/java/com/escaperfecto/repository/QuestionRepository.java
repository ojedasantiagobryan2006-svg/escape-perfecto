package com.escaperfecto.repository;

import com.escaperfecto.model.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> findAll();
}

