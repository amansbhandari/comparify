import { Button, TextField, Box, Grid, MenuItem } from "@material-ui/core";
import { useFormik } from "formik";
import React, { useEffect } from "react";
import { useSelector } from "react-redux";
import { authenication } from "../../store/thunk/userThunkCreators";
import useStyles from "../../hooks/use-styles";
import { useNavigate } from "react-router-dom";
import httpClient from "../../store/thunk/interceptor";

const style = {
  root: {
    display: "inline-block",
    margin: "30px 0px",
  },
  button: {
    marginTop: "16px",
    marginBottom: "8px",
  },
  buttonContainer: {
    display: "flex",
    justifyContent: "end",
  },
};
const ResetPassword = (props) => {
  const navigate = useNavigate();
  const classes = useStyles(style);
  const authentication = useSelector((state) => state.authentication);
  const formik = useFormik({
    initialValues: {},
    onSubmit: async (values, actions) => {
      const {
        userIdentifier,
        securityQuestionOne,
        securityAnswerOne,
        securityQuestionTwo,
        securityAnswerTwo,
        securityQuestionThree,
        securityAnswerThree,
      } = values;
      const question1 = {
        userIdentifier: userIdentifier,
        question: securityQuestionOne,
        answer: securityAnswerOne,
      };
      const question2 = {
        userIdentifier: userIdentifier,
        question: securityQuestionTwo,
        answer: securityAnswerTwo,
      };
      const question3 = {
        userIdentifier: userIdentifier,
        question: securityQuestionThree,
        answer: securityAnswerThree,
      };
      if (userIdentifier === undefined) {
        alert("Please enter user name");
        return;
      }
      if(question1.question === undefined || question1.answer === undefined|| question2.question === undefined || question2.answer === undefined || question3.question === undefined || question3.answer === undefined|| question1.question === "" || question1.answer === "" || question2.question === "" || question2.answer === "" || question3.question === "" || question3.answer === "")
      {
        alert("Please enter all the fields");
        return;
      }
      if (
        question1.question === question2.question ||
        question1.question === question3.question ||
        question2.question === question3.question
      ) {
        alert("Please select different questions");
        return;
      }
  
      var questionsObject = [];
      var questionArray =[];
      const credential = { params: { userIdentifier: userIdentifier } };
     
      try {
       questionsObject = await httpClient.get("/securityQuestion/getAll",credential);
      } catch (error) {
        console.log(error);
        alert("can not find user");
        return;
      }
      console.log(questionsObject);
      
      questionArray = questionsObject.data;
     
      if(!validate(questionArray[0],question1)&&
        !validate(questionArray[1],question1)&&
        !validate(questionArray[2],question1)){  
          alert(question1.answer+" is not a valid answer for "+question1.question);
          return;
      }
      if(!validate(questionArray[0],question2)&&
        !validate(questionArray[1],question2)&&
        !validate(questionArray[2],question2)){  
          alert(question2.answer+" is not a valid answer for "+question2.question);
          return;
      }
      if(!validate(questionArray[0],question3)&&
        !validate(questionArray[1],question3)&&
        !validate(questionArray[2],question3)){  
          alert(question3.answer+" is not a valid answer for "+question3.question);
          return;
      }
     
      navigate("/newPassword");
    },
  });
  const validate = (inputs,stored) => {
    if(inputs.question === stored.question && inputs.answer === stored.answer){
        return true;
    }
       return false;
    
  }

  const { resetForm } = formik;
  useEffect(() => {
    if (!authenication.token) {
      resetForm(props.values);
    }
  }, [authentication, resetForm, props.values]);
  const questions = [
    {
      id: "Question one",
      value: "what is your name",
      label: "what is your name",
    },
    {
      id: "Question Two",
      value: "what is your favorate color",
      label: "what is your favorate color",
    },
    {
      id: "Question three",
      value: "when is your birthday",
      label: "when is your birthday",
    },
  ];
  return (
    <Grid container className={classes.root}>
      <Box>
        <form onSubmit={formik.handleSubmit}>
          <Grid>
            <Grid item>
              <TextField
                fullWidth
                margin="normal"
                variant="outlined"
                id="userIdentifier"
                name="userIdentifier"
                label="User Identifier"
                autoComplete="off"
                value={formik.values.userIdentifier}
                onChange={formik.handleChange}
                error={
                  formik.touched.userIdentifier &&
                  Boolean(formik.errors.userIdentifier)
                }
                helperText={
                  formik.touched.userIdentifier && formik.errors.userIdentifier
                }
                inputProps={{
                  autoComplete: "off",
                }}
              />
            </Grid>
            <Grid item>
              <TextField
                fullWidth
                margin="normal"
                select
                variant="outlined"
                id="securityQuestionOne"
                name="securityQuestionOne"
                label="First Security Question"
                autoComplete="off"
                value={formik.values.securityQuestionOne}
                onChange={formik.handleChange}
                error={
                  formik.touched.securityQuestionOne &&
                  Boolean(formik.errors.securityQuestionOne)
                }
                helperText={
                  formik.touched.securityQuestionOne &&
                  formik.errors.securityQuestionOne
                }
                inputProps={{
                  autoComplete: "off",
                }}
              >
                {questions.map((question) => (
                  <MenuItem key={question.id} value={question.value}>
                    {question.label}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item>
              <TextField
                fullWidth
                margin="normal"
                variant="outlined"
                id="securityAnswerOne"
                name="securityAnswerOne"
                label="Security Answer"
                autoComplete="off"
                value={formik.values.securityAnswerOne}
                onChange={formik.handleChange}
                error={
                  formik.touched.securityAnswerOne &&
                  Boolean(formik.errors.securityAnswerOne)
                }
                helperText={
                  formik.touched.securityAnswerOne &&
                  formik.errors.securityAnswerOne
                }
                inputProps={{
                  autoComplete: "off",
                }}
              />
            </Grid>

            <Grid item>
              <TextField
                fullWidth
                margin="normal"
                select
                variant="outlined"
                id="securityQuestionTwo"
                name="securityQuestionTwo"
                label="Second Security Question"
                autoComplete="off"
                value={formik.values.securityQuestionTwo}
                onChange={formik.handleChange}
                error={
                  formik.touched.securityQuestion &&
                  Boolean(formik.errors.securityQuestion)
                }
                helperText={
                  formik.touched.securityQuestion &&
                  formik.errors.securityQuestion
                }
                inputProps={{
                  autoComplete: "off",
                }}
              >
                {questions.map((question) => (
                  <MenuItem key={question.id} value={question.value}>
                    {question.label}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item>
              <TextField
                fullWidth
                margin="normal"
                variant="outlined"
                id="securityAnswerTwo"
                name="securityAnswerTwo"
                label="Security Answer"
                autoComplete="off"
                value={formik.values.securityAnswerTwo}
                onChange={formik.handleChange}
                error={
                  formik.touched.securityAnswerTwo &&
                  Boolean(formik.errors.securityAnswerTwo)
                }
                helperText={
                  formik.touched.securityAnswerTwo &&
                  formik.errors.securityAnswerTwo
                }
                inputProps={{
                  autoComplete: "off",
                }}
              />
            </Grid>

            <Grid item>
              <TextField
                fullWidth
                margin="normal"
                select
                variant="outlined"
                id="securityQuestionThree"
                name="securityQuestionThree"
                label="Third Security QuestionThree"
                autoComplete="off"
                value={formik.values.securityQuestionThree}
                onChange={formik.handleChange}
                error={
                  formik.touched.securityQuestionThree &&
                  Boolean(formik.errors.securityQuestionThree)
                }
                helperText={
                  formik.touched.securityQuestionThree &&
                  formik.errors.securityQuestionThree
                }
                inputProps={{
                  autoComplete: "off",
                }}
              >
                {questions.map((question) => (
                  <MenuItem key={question.id} value={question.value}>
                    {question.label}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item>
              <TextField
                fullWidth
                margin="normal"
                variant="outlined"
                id="securityAnswerThree"
                name="securityAnswerThree"
                label="Security Answer"
                autoComplete="off"
                value={formik.values.securityAnswerThree}
                onChange={formik.handleChange}
                error={
                  formik.touched.securityAnswerThree &&
                  Boolean(formik.errors.securityAnswerThree)
                }
                helperText={
                  formik.touched.securityAnswerThree &&
                  formik.errors.securityAnswerThree
                }
                inputProps={{
                  autoComplete: "off",
                }}
              />
            </Grid>
            <Grid item className={classes.buttonContainer}>
              <Button
                color="primary"
                variant="contained"
                type="submit"
                className={classes.button}
              >
                Submit
              </Button>
            </Grid>
          </Grid>
        </form>
      </Box>
    </Grid>
  );
};
export default ResetPassword;
