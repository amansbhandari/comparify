package ca.dal.comparify.feedback.controller;

import ca.dal.comparify.feedback.model.Feedback;
import ca.dal.comparify.feedback.services.FeedbackService;
import ca.dal.comparify.framework.notification.mail.MailService;
import ca.dal.comparify.model.HashModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
public class RestUpdateController {


    /**
     * @author Meghna Rupchandani
     */
    @Autowired
    FeedbackService feedbackService;

    @Autowired
    private MailService mailService;

    @PostMapping("/feedback")
    @ResponseBody
    public String userFeedback(@RequestBody Feedback fb) {
        fb.setDate(LocalDate.now());
        boolean data = feedbackService.addFeedback(fb);
        sendEmail(fb);
        System.out.println(data);
        if (data) {
            return "Feedback added succesfully!";
        } else {
            return "Unable to add the feedback";
        }
    }

    public void sendEmail(Feedback feedback) {

        String subject = "Thank you for your feedback!!";

        HashModel model = new HashModel();

        model.put("feedback", feedback.getUsersFeedback());
        model.put("suggestion", feedback.getSuggestions());

        mailService.send(feedback.getEmail(), subject, "feedback-template.html",model);
    }

}
