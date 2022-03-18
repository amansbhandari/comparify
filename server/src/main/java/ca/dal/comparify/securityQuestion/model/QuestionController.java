package ca.dal.comparify.securityQuestion.model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class QuestionController {
    @RequestMapping("/question")
    public String question() {
        return "What is your mother's maiden name?";
    }

    @PostMapping("/answer")
    public String answer(String answer) {
        if (answer.equals("Doe")) {
            return "Correct!";
        } else {
            return "Incorrect!";
        }
    }
}   