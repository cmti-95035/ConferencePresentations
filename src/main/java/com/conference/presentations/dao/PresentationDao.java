package com.conference.presentations.dao;

import com.conference.presentations.model.Presentation;

import java.util.List;

public interface PresentationDao {
    Presentation findById(Integer id);

    List<Presentation> findAll();

    List<Presentation> findAllByField(Integer fieldId);

    List<Presentation> findAllByConferenceId(Integer conferenceId);

    void save(Presentation presentation);

    void update(Presentation presentation);

    void delete(Integer id);
}
