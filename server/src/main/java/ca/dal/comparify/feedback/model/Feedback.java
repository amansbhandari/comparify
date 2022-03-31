package ca.dal.comparify.feedback.model;

import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;

public class Feedback{


    /**
     * @author Meghna Rupchandani
     */
    @BsonId
    private String id;
    @BsonProperty
    private String email, Usersfeedback, suggestions;
    @BsonProperty
    private LocalDate date ;

    public Feedback() {

    }

    public Feedback(String email, String Usersfeedback, String suggestions) {
        this.id = UUIDUtils.generate();
        this.email = email;
        this.Usersfeedback = Usersfeedback;
        this.suggestions = suggestions;
        this.date = LocalDate.now();
    }

//    public Feedback(String id, String email, String Usersfeedback, String suggestions) {
//        this.id = id;
//        this.email = email;
//        this.Usersfeedback = Usersfeedback;
//        this.suggestions = suggestions;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", feedback='" + Usersfeedback + '\'' +
                ", suggestions='" + suggestions + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsersfeedback() {
        return Usersfeedback;
    }

    public void setUsersfeedback(String usersfeedback) {
        this.Usersfeedback = usersfeedback;
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

