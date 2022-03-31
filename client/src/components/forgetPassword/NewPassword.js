import { Button, TextField, Box, Grid } from "@material-ui/core";
import { useFormik } from "formik";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
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
const NewPassword = (props) => {
  const navigate = useNavigate();
  const classes = useStyles(style);
  const dispatch = useDispatch();
  const authentication = useSelector((state) => state.authentication);
  const formik = useFormik({
    initialValues: {},
    onSubmit: async (values, actions) => {
      const { userIdentifier, newPassword, confirmPassword } = values;
      const user = { userIdentifier: userIdentifier, newPassword: newPassword };

      if (newPassword !== confirmPassword) {
        alert("Password does not match");
        return;
      }
      try {
         await httpClient.post("/authentication", user);
         alert("password reset successfully");
      } catch (error) {
        console.log(error);
      }
      

      dispatch(navigate("/home"));
    },
  });

  const { resetForm } = formik;
  useEffect(() => {
    if (!authenication.token) {
      resetForm(props.values);
    }
  }, [authentication, resetForm, props.values]);

  return (
    <Grid container className={classes.root}>
      <Box>
        <form onSubmit={formik.handleSubmit}>
          <Grid>
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
          <Grid>
            <TextField
              fullWidth
              margin="normal"
              variant="outlined"
              id="newPassword"
              name="newPassword"
              label="newPassword"
              autoComplete="off"
              value={formik.values.newPassword}
              onChange={formik.handleChange}
              error={
                formik.touched.newPassword && Boolean(formik.errors.newPassword)
              }
              helperText={
                formik.touched.newPassword && formik.errors.newPassword
              }
              inputProps={{
                autoComplete: "off",
              }}
            />
          </Grid>
          <Grid>
            <TextField
              fullWidth
              margin="normal"
              variant="outlined"
              id="confirmPassword"
              name="confirmPassword"
              label="confirmPassword"
              autoComplete="off"
              value={formik.values.confirmPassword}
              onChange={formik.handleChange}
              error={
                formik.touched.confirmPassword &&
                Boolean(formik.errors.confirmPassword)
              }
              helperText={
                formik.touched.confirmPassword && formik.errors.confirmPassword
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
        </form>
      </Box>
    </Grid>
  );
};
export default NewPassword;
