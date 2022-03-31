package ca.dal.comparify.feedback.controller;


import ca.dal.comparify.feedback.model.Feedback;
import ca.dal.comparify.feedback.repository.FeedbackRepository;
import ca.dal.comparify.feedback.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * @author Meghna Rupchandani
 */
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping("/feedback")
    public String feedback() {
        return "feedback";
    }

}
