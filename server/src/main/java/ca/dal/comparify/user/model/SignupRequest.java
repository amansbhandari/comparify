package ca.dal.comparify.user.model;


import ca.dal.comparify.utils.StringUtils;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @author Meghna Rupchandani
 */

public class SignupRequest {

  @BsonId
  private String id;

  private String firstName;

  private String lastName;

  private String username;

  private String email;

  private String securityQuestion1;

  private String securityQuestion2;

  private String securityQuestion3;

  private String Answer1;

  private String Answer2;

  private String Answer3;

  private String password;

  private String confirmPassword;

  private double points;

  private String type;


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSecurityQuestion1() {
    return securityQuestion1;
  }

  public void setSecurityQuestion1(String securityQuestion1) {
    this.securityQuestion1 = securityQuestion1;
  }

  public String getSecurityQuestion2() {
    return securityQuestion2;
  }

  public void setSecurityQuestion2(String securityQuestion2) {
    this.securityQuestion2 = securityQuestion2;
  }

  public String getSecurityQuestion3() {
    return securityQuestion3;
  }

  public void setSecurityQuestion3(String securityQuestion3) {
    this.securityQuestion3 = securityQuestion3;
  }

  public String getAnswer1() {
    return Answer1;
  }

  public void setAnswer1(String answer1) {
    Answer1 = answer1;
  }

  public String getAnswer2() {
    return Answer2;
  }

  public void setAnswer2(String answer2) {
    Answer2 = answer2;
  }

  public String getAnswer3() {
    return Answer3;
  }

  public void setAnswer3(String answer3) {
    Answer3 = answer3;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public double getPoints() {
    return points;
  }

  public void setPoints(double points) {
    this.points = points;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public SignupRequest(String firstName, String lastName, String username, String email, String securityQuestion1, String securityQuestion2, String securityQuestion3, String Answer1, String Answer2, String Answer3, String password, String confirmPassword, double points, String type) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.securityQuestion1 = securityQuestion1;
    this.securityQuestion2 = securityQuestion2;
    this.securityQuestion3 = securityQuestion3;
    this.Answer1=Answer1;
    this.Answer2=Answer2;
    this.Answer3=Answer3;
    this.password = password;
    this.confirmPassword = confirmPassword;
    this.points=points;
    this.type=type;
  }

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
          Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  public boolean validateEmail() {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    return true;
  }

  public boolean validate() {

    if (StringUtils.isAnyEmpty(firstName, lastName, username, email, securityQuestion1, securityQuestion2, securityQuestion3, Answer1, Answer2, Answer3, password, confirmPassword)) {
      return false;
    }

    if (!password.equals(confirmPassword)) {
      return false;
    }
  return true;
  }

  // digit + lowercase char + uppercase char + punctuation + symbol
  private static final String PATTERN_PASSWORD =
              "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

  private final Pattern objPattern = Pattern.compile(PATTERN_PASSWORD);

  public boolean HasValidPasswordPattern(String password) {
        Matcher matcher = objPattern.matcher(password);
        return true;
      }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}


