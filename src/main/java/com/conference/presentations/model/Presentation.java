package com.conference.presentations.model;

public class Presentation {
    Integer id;

    Integer userId;

    Integer conferenceId;

    Conference conference;

    String title;

    Integer fieldId;

    String authors;

    String abs;

    public boolean isNew() {
        return (this.id == null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
}
