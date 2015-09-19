package com.conference.presentations.web;

import com.conference.presentations.model.Presentation;
import com.conference.presentations.service.PresentationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PresentationController {
    private final Logger logger = LoggerFactory.getLogger(PresentationController.class);

//    @Autowired
//    ConferenceFormValidator presentationFormValidator;
//
//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(presentationFormValidator);
//    }

    private PresentationService presentationService;

    @Autowired
    public void setPresentationService(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    // list page
    @RequestMapping(value = "/presentations", method = RequestMethod.GET)
    public String showAllPresentations(Model model) {

        logger.debug("showAllPresentations()");
        model.addAttribute("presentations", presentationService.findAll());
        return "presentations/list";

    }

    // save or update presentation
    @RequestMapping(value = "/presentations", method = RequestMethod.POST)
    public String saveOrUpdatePresentation(@ModelAttribute("presentationForm") @Validated Presentation presentation,
                                         BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        logger.debug("saveOrUpdatePresentation() : {}", presentation);

        if (result.hasErrors()) {
            return "presentations/presentationform";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            if(presentation.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Presentation added successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Presentation updated successfully!");
            }

            presentationService.saveOrUpdate(presentation);

            // POST/REDIRECT/GET
            return "redirect:/presentations/" + presentation.getId();

            // POST/FORWARD/GET
            // return "presentation/list";

        }

    }

    // show add presentation form
    @RequestMapping(value = "/presentations/upload", method = RequestMethod.GET)
    public String showAddPresentationForm(Model model) {

        logger.debug("showAddPresentationForm()");

        Presentation presentation = new Presentation();

        // set default value
        presentation.setFieldId(2);
        presentation.setUserId(1);
        presentation.setTitle("Great Presentation");

        presentation.setConferenceId(1000);
        presentation.setAbs("show me the money");
        presentation.setAuthors("abc@test.com;xyz@gmail.com");

        model.addAttribute("presentationForm", presentation);

        return "presentations/presentationform";

    }

    // show update form
    @RequestMapping(value = "/presentations/{id}/update", method = RequestMethod.GET)
    public String showUpdatePresentationForm(@PathVariable("id") int id, Model model) {

        logger.debug("showUpdatePresentationForm() : {}", id);

        Presentation presentation = presentationService.findById(id);
        model.addAttribute("presentationForm", presentation);

        return "presentations/presentationform";

    }

    // delete presentation
    @RequestMapping(value = "/presentations/{id}/delete", method = RequestMethod.POST)
    public String deletePresentation(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        logger.debug("deletePresentation() : {}", id);

        presentationService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Presentation is deleted!");

        return "redirect:/presentations";

    }

    // show presentation
    @RequestMapping(value = "/presentations/{id}", method = RequestMethod.GET)
    public String showPresentation(@PathVariable("id") int id, Model model) {

        logger.debug("showPresentation() id: {}", id);

        Presentation presentation = presentationService.findById(id);
        if (presentation == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Presentation not found");
        }
        model.addAttribute("presentation", presentation);

        return "presentations/show";

    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("presentation/show");
        model.addObject("msg", "presentation not found");

        return model;

    }
}
