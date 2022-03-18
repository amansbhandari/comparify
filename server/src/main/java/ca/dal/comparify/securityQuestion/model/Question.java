package ca.dal.comparify.securityQuestion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
    private @Id @GeneratedValue Long id;
    private String question;
    private String answer;

    private Question() {
    }

    public Question(String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }
}