import { Box, ThemeProvider } from "@material-ui/core";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useStyles from "../../hooks/use-styles";

import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';


import LockOutlinedIcon from '@mui/icons-material/LockOutlined';

import { useSelector } from "react-redux";
import { toast } from "react-toastify";
import Authentication from "../authenication/Authentication";

import { theme } from "./../../themes/theme";

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
    // const initialValues = {
    //     userIdentifier: '', secret: ''
    // }

    const forgotPasswordHandler = (e) => {
        navigate("/resetPassword");
    }

    const registerHandler = (e) => {
        navigate("/register");
    }

    useEffect(() => {
        if (authentication.token) {
            navigate("/home");
        } else {
            toast.error(authentication.message)
        }

    }, [authentication, navigate])


    return (<>
        <ThemeProvider theme={theme}>
            <Box className={classes.root}>
                <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                    <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                    Sign in
                </Typography>

                <Authentication />
                <Grid container>
                    <Grid item xs>
                        <Box onClick={forgotPasswordHandler}
                            sx={{
                                cursor: 'pointer',
                                textDecoration: 'underline',
                                textDecorationColor: '#1976d2',
                            }}>
                            <Typography variant="body2" sx={{ color: '#1976d2' }}>
                                {"Forgot password?"}
                            </Typography>
                        </Box>
                    </Grid>
                    <Grid item>
                        <Box onClick={registerHandler}
                            sx={{
                                cursor: 'pointer',
                                textDecoration: 'underline',
                                textDecorationColor: '#1976d2'
                            }}>
                            <Typography variant="body2" sx={{ color: '#1976d2' }}>
                                {"Don't have an account? Sign Up"}
                            </Typography>
                        </Box>
                    </Grid>
                </Grid>
            </Box>
        </ThemeProvider>
    </>)
}

export default LandingPage;