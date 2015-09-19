package com.conference.presentations.service;


import com.conference.presentations.dao.ConferenceDao;
import com.conference.presentations.model.Conference;
import com.conference.presentations.model.ResearchField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConferenceServiceImpl implements ConferenceService {
    ConferenceDao conferenceDao;

    @Autowired
    public void setConferenceDao(ConferenceDao conferenceDao) { this.conferenceDao = conferenceDao; }

    @Override
    public Conference findById(Integer id) {
        return conferenceDao.findById(id);
    }

    @Override
    public List<Conference> findAll() {
        return conferenceDao.findAll();
    }

    @Override
    public void saveOrUpdate(Conference conference) {
        if(conferenceDao.findById(conference.getId()) == null){
            conferenceDao.save(conference);
        } else {
            conferenceDao.update(conference);
        }
    }

    @Override
    public void delete(int id) {
        conferenceDao.delete(id);
    }

    @Override
    public List<ResearchField> findAllFields() {
        return null;
    }
}
