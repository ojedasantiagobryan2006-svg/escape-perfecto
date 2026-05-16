package com.escaperfecto.repository;

import com.escaperfecto.model.Prize;

import java.util.List;

public interface PrizeRepository {
    List<Prize> findAll();
}

