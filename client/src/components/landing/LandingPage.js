import { Box } from "@material-ui/core";
import React, { useEffect } from "react";
import { useNavigate  } from "react-router-dom";
import Authentication from "../authenication/Authentication";
import useStyles from "../../hooks/use-styles";


import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';
import Paper from '@mui/material/Paper';




import LockOutlinedIcon from '@mui/icons-material/LockOutlined';

import logo from '../../assets/logo/logo-256.png';
import { useSelector } from "react-redux";
import { toast } from "react-toastify";



const style = {
    root: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        margin: "64px 128px"
    },
    imageContainer: {
        display: 'flex',
        background: '#3f50b5',
        alignItems: 'center',
        justifyContent: 'center'
    },
    imageGrid: {
        display: "flex",
        justifyContent: "center",
        alignItems: "center"
    },
    imageBox: {
        background: 'white',
        borderRadius: '50%',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
    },
    imageFrame: {
        display: 'inline-block',
        height: 'fit-content !important'
    },
    image: {
        padding: "50px"
    },
    title: {
        color: "#FFFFFF",
        textAlign: "center",
        fontSize: "2rem !important"
    },
    titleContainer: {
        margin: "0px 0px 1.5rem 0px",
        fontWeight: "700 !important"
    }

}

const LandingPage = (props) => {

    const classes = useStyles(style);
    const authentication = useSelector((state) => state.authentication)
    const navigate = useNavigate();
    const initialValues = {
        userIdentifier: '', secret: ''
    }

    useEffect(() => {
        if (authentication.token) {
            navigate("/home");
        } else {
            toast.error(authentication.message)
        }

    },[authentication, navigate])
  


    return (
      <>
        {authentication.token != null ? (
          <></>
        ) : (
          <Grid container component="main" sx={{ height: "100vh" }}>
            <Grid
              item
              xs={false}
              sm={5}
              md={6}
              className={classes.imageContainer}
            >
              <Grid container direction="column" className={classes.imageGrid}>
                <Grid>
                  <Box className={classes.titleContainer}>
                    <Typography
                      component="h1"
                      variant="h5"
                      className={classes.title}
                    >
                      Comparify
                    </Typography>
                  </Box>
                </Grid>
                <Grid>
                  <section className={classes.imageBox}>
                    <Box className={classes.imageFrame}>
                      <img src={logo} alt="Logo" className={classes.image} />
                    </Box>
                  </section>
                </Grid>
              </Grid>
            </Grid>

            <Grid
              item
              xs={12}
              sm={7}
              md={6}
              component={Paper}
              elevation={6}
              square
            >
              <Box className={classes.root}>
                <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
                  <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                  Sign in
                </Typography>

                <Authentication values={initialValues}></Authentication>
                <Grid container>
                  <Grid item xs>
                    <Link href="/resetPassword" variant="body2">
                      Forgot password?
                    </Link>
                  </Grid>
                  <Grid item>
                    <Link href="#" variant="body2">
                      {"Don't have an account? Sign Up"}
                    </Link>
                  </Grid>
                </Grid>
              </Box>
            </Grid>
          </Grid>
        )}
      </>
    );
}

export default LandingPage;