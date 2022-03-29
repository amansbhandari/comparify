package ca.dal.comparify.securityQuestion;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.dal.comparify.securityQuestion.model.Question;
import ca.dal.comparify.securityQuestion.repository.QuestionRepository;


@RestController
@RequestMapping("/securityQuestion")      
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/add")
    public int addQuestion(@RequestBody Question question) {  
        return questionRepository.addQuestion(question);
    }

    @GetMapping("/getAll")
    public List<Question> getQuestion(@RequestParam String userIdentifier) {
        
        return  questionRepository.getAllQuestion(userIdentifier);
       
    }
    @GetMapping("/getOne")
    public Question getOneQuestion(@RequestParam String userIdentifier) {
        
        return  questionRepository.getOneQuestionById(userIdentifier);
       
    }

    @PutMapping("/update")
    public boolean updateAnswer(@RequestParam String userIdentifier,@RequestParam String question,@RequestParam String answer) {
        return questionRepository.updateAnswer(userIdentifier, question, answer);
    }

    @DeleteMapping("/delete")
    public boolean deleteQuestion(@RequestParam String userIdentifier,@RequestParam String question) {   
        return questionRepository.deleteQuestion(userIdentifier, question);
    }
    
}   