package com.conference.presentations.dao;

import com.conference.presentations.model.Conference;

import java.util.List;

public interface ConferenceDao {
    Conference findById(Integer id);

    List<Conference> findAll();

    void save(Conference Conference);

    void update(Conference Conference);

    void delete(Integer id);
}
