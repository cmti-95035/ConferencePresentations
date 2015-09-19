package com.conference.presentations.dao;

import com.conference.presentations.model.ResearchField;

import java.util.List;

public interface ResearchFieldDao {
    ResearchField findById(Integer id);

    List<ResearchField> findAll();

    Integer findByField(String field);
}
