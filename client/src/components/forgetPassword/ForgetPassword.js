import { Button, TextField, Box, Grid, MenuItem } from "@material-ui/core";
import { useFormik } from "formik";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { authenication } from "../../store/thunk/userThunkCreators";
import useStyles from "../../hooks/use-styles";
import { useNavigate } from "react-router-dom";

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
const ForgetPassword = (props) => {
  const navigate = useNavigate();
  const classes = useStyles(style);
  const dispatch = useDispatch();
  const authentication = useSelector((state) => state.authentication);
  const formik = useFormik({
    initialValues: {},
    onSubmit: (values, actions) => {
      const { userIdentifier, secret } = values;
      if (!userIdentifier || !secret) {
        return;
      }
      dispatch(
        authenication({
          user_identifier: userIdentifier,
          secret,
        })
      );
    },
  });
  const { resetForm } = formik;
  useEffect(() => {
    if (!authenication.token) {
      resetForm(props.values);
    }
  }, [authentication, resetForm, props.values]);
  const questions = [
    {
      id: "random",
      value: "random value",
      label: "random question",
    },
  ];
  return (
    <Grid container className={classes.root}>
      <Box
        onClick={() => {
          navigate("/forget");
        }}
      >
        Forgot password?
      </Box>
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
                id="securityQuestion"
                name="securityQuestion"
                label="Security Question"
                autoComplete="off"
                value={formik.values.securityQuestion}
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
                id="securityAnswer"
                name="securityAnswer"
                label="Security Answer"
                autoComplete="off"
                value={formik.values.securityAnswer}
                onChange={formik.handleChange}
                error={
                  formik.touched.securityAnswer &&
                  Boolean(formik.errors.securityAnswer)
                }
                helperText={
                  formik.touched.securityAnswer && formik.errors.securityAnswer
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
export default ForgetPassword;
