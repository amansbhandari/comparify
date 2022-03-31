package ca.dal.comparify.feedback.controller;

import ca.dal.comparify.emailService.EmailSenderService;
import ca.dal.comparify.feedback.model.Feedback;
import ca.dal.comparify.feedback.services.FeedbackService;
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

    @PostMapping("/feedback")
    @ResponseBody
    public String userFeedback(@ModelAttribute Feedback fb) {
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

    @Autowired
    EmailSenderService emailService;

    public void sendEmail(Feedback feedback) {

        emailService.sendEmail(feedback.getEmail(), "Your Feedback:" + feedback.getUsersfeedback()+"\n"+"Your Suggestion:" + feedback.getSuggestions(),"Thank you for your feedback!!");
    }

}
