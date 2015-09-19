package com.conference.presentations.service;

import com.conference.presentations.model.Presentation;

import java.util.List;

public interface PresentationService {
    Presentation findById(Integer id);

    List<Presentation> findAll();

    void saveOrUpdate(Presentation user);

    void delete(int id);
}
