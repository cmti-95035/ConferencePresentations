package com.conference.presentations.service;

import com.conference.presentations.dao.PresentationDao;
import com.conference.presentations.model.Presentation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PresentationServiceImpl implements PresentationService {
    PresentationDao presentationDao;

    @Autowired
    public void setPresentationDao(PresentationDao presentationDao) {
        this.presentationDao = presentationDao;
    }

    @Override
    public Presentation findById(Integer id) {
        return presentationDao.findById(id);
    }

    @Override
    public List<Presentation> findAll() {
        return presentationDao.findAll();
    }

    @Override
    public List<Presentation> findAllByField(Integer fieldId) {
        return presentationDao.findAllByField(fieldId);
    }

    @Override
    public List<Presentation> findAllByConferenceId(Integer conferenceId) {
        return presentationDao.findAllByConferenceId(conferenceId);
    }

    @Override
    public void saveOrUpdate(Presentation presentation) {
        if(findById(presentation.getId()) == null) {
            presentationDao.save(presentation);
        } else {
            presentationDao.update(presentation);
        }
    }

    @Override
    public void delete(int id) {
        presentationDao.delete(id);
    }
}
