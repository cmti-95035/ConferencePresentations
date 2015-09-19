package com.conference.presentations.service;

import com.conference.presentations.model.Conference;
import com.conference.presentations.model.ResearchField;

import java.util.List;

public interface ConferenceService {
    Conference findById(Integer id);

    List<Conference> findAll();

    void saveOrUpdate(Conference Conference);

    void delete(int id);

    List<ResearchField> findAllFields();
}
