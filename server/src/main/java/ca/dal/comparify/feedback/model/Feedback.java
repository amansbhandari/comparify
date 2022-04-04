package ca.dal.comparify.feedback.model;

import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Feedback{


    /**
     * @author Meghna Rupchandani
     */
    @BsonId
    private ObjectId id;
    @BsonProperty
    private String email, usersFeedback, suggestions;
    @BsonProperty
    private LocalDate date ;

    public Feedback() {

    }

    public Feedback(ObjectId id, String email, String usersFeedback, String suggestions, LocalDate date) {
        this.id = id;
        this.email = email;
        this.usersFeedback = usersFeedback;
        this.suggestions = suggestions;
        this.date = date;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsersFeedback() {
        return usersFeedback;
    }

    public void setUsersFeedback(String usersFeedback) {
        this.usersFeedback = usersFeedback;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

