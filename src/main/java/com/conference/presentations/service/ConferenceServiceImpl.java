package com.conference.presentations.service;


import com.conference.presentations.dao.ConferenceDao;
import com.conference.presentations.dao.ResearchFieldDao;
import com.conference.presentations.model.Conference;
import com.conference.presentations.model.ResearchField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConferenceServiceImpl implements ConferenceService {
    ConferenceDao conferenceDao;

    ResearchFieldDao researchFieldDao;

    @Autowired
    public void setResearchFieldDao(ResearchFieldDao researchFieldDao) { this.researchFieldDao =  researchFieldDao;}
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
        if(conference.getId() == null || conferenceDao.findById(conference.getId()) == null){
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
        return researchFieldDao.findAll();
    }
}
