package com.conference.presentations.web;

import com.conference.presentations.model.Conference;
import com.conference.presentations.service.ConferenceService;
import com.conference.presentations.validator.ConferenceFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ConferenceController {
    private final Logger logger = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    ConferenceFormValidator conferenceFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(conferenceFormValidator);
    }

    private ConferenceService conferenceService;

    @Autowired
    public void setConferenceService(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    // list page
    @RequestMapping(value = "/conferences", method = RequestMethod.GET)
    public String showAllConferences(Model model) {

        logger.debug("showAllConferences()");
        model.addAttribute("conferences", conferenceService.findAll());
        return "conferences/list";

    }

    // save or update conference
    @RequestMapping(value = "/conferences", method = RequestMethod.POST)
    public String saveOrUpdateConference(@ModelAttribute("conferenceForm") @Validated Conference conference,
                                   BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        logger.debug("saveOrUpdateConference() : {}", conference);

        if (result.hasErrors()) {
            return "conferences/conferenceform";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            if(conference.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Conference added successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Conference updated successfully!");
            }

            conferenceService.saveOrUpdate(conference);

            // POST/REDIRECT/GET
            return "redirect:/conferences/" + conference.getId();

            // POST/FORWARD/GET
            // return "conference/list";

        }

    }

    // show add conference form
    @RequestMapping(value = "/conferences/add", method = RequestMethod.GET)
    public String showAddConferenceForm(Model model) {

        logger.debug("showAddConferenceForm()");

        Conference conference = new Conference();

        // set default value
        conference.setName("AWS re:Invent");
        conference.setVenue("Las Vegas");
        conference.setConferenceTime("Octorber 6-9, 2015");

        conference.setOrganizer("Amazon");
        conference.setWebsite("https://reinvent.awsevents.com/");
        conference.setEmails("abc@test.com;xyz@gmail.com");

        model.addAttribute("conferenceForm", conference);

        return "conferences/conferenceform";

    }

    // show update form
    @RequestMapping(value = "/conferences/{id}/update", method = RequestMethod.GET)
    public String showUpdateConferenceForm(@PathVariable("id") int id, Model model) {

        logger.debug("showUpdateConferenceForm() : {}", id);

        Conference conference = conferenceService.findById(id);
        model.addAttribute("conferenceForm", conference);

        return "conferences/conferenceform";

    }

    // delete conference
    @RequestMapping(value = "/conferences/{id}/delete", method = RequestMethod.POST)
    public String deleteConference(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        logger.debug("deleteConference() : {}", id);

        conferenceService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Conference is deleted!");

        return "redirect:/conferences";

    }

    // show conference
    @RequestMapping(value = "/conferences/{id}", method = RequestMethod.GET)
    public String showConference(@PathVariable("id") int id, Model model) {

        logger.debug("showConference() id: {}", id);

        Conference conference = conferenceService.findById(id);
        if (conference == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Conference not found");
        }
        model.addAttribute("conference", conference);

        return "conferences/show";

    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("conference/show");
        model.addObject("msg", "conference not found");

        return model;

    }
}
