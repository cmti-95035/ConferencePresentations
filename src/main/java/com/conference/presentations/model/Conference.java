package com.conference.presentations.model;

import java.util.List;

public class Conference {
    // form:hidden - hidden value
    Integer id;

    // form:input - textbox
    String name;

    // form:input - textbox
    String venue;

    // form:input - textbox
    String conferenceTime;

    // form:input - textbox
    String organizer;

    // form:input - textbox
    String website;

    // form:input - textarea
    String emails;

    public boolean isNew() {
        return (this.id == null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getConferenceTime() {
        return conferenceTime;
    }

    public void setConferenceTime(String conferenceTime) {
        this.conferenceTime = conferenceTime;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }
}
