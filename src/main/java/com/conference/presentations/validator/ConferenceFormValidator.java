package com.conference.presentations.validator;


import com.conference.presentations.model.Conference;
import com.conference.presentations.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ConferenceFormValidator implements Validator {
//    @Autowired
//    @Qualifier("emailValidator")
//    EmailValidator emailValidator;

    @Autowired
    ConferenceService conferenceService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Conference.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Conference conference = (Conference) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.conferenceForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "NotEmpty.conferenceForm.website");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "venue", "NotEmpty.conferenceForm.venue");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "organizer", "NotEmpty.conferenceForm.organizer");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "conferenceTime","NotEmpty.conferenceForm.conferenceTime");

    }
}
