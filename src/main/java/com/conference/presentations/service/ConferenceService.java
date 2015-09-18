package com.conference.presentations.service;

import com.conference.presentations.model.Conference;

import java.util.List;

public interface ConferenceService {
    Conference findById(Integer id);

    List<Conference> findAll();

    void saveOrUpdate(Conference Conference);

    void delete(int id);
    
}
