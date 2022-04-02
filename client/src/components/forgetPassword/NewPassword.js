import { Button, TextField, Box, Grid, ThemeProvider } from "@material-ui/core";
import { useFormik } from "formik";
import React, { useEffect } from "react";
import { useSelector } from "react-redux";
import { authenication } from "../../store/thunk/userThunkCreators";
import useStyles from "../../hooks/use-styles";
import { useNavigate } from "react-router-dom";
import httpClient from "../../store/thunk/interceptor";
import { theme } from "./../../themes/theme";
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';


import LockOutlinedIcon from '@mui/icons-material/LockOutlined';

const style = {
  root: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    margin: "20px 0px"
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
      navigate("/login");
    },
  });

  const { resetForm } = formik;
  useEffect(() => {
    if (!authenication.token) {
      resetForm(props.values);
    }
  }, [authentication, resetForm, props.values]);

  return (
    <ThemeProvider theme={theme}>
    <Grid >
    <Box className={classes.root}>
                <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                    <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                    Reset Password
                </Typography>
        <form onSubmit={formik.handleSubmit}  style={{'width' : '80%'}}>
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
    </ThemeProvider>
  );
};
export default NewPassword;
