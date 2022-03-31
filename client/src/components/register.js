import { Box } from "@material-ui/core";
import React from "react";
import useStyles from "../hooks/use-styles";
import RegisterPage from "./RegisterPage";
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';
import { useNavigate } from "react-router-dom";


import LockOutlinedIcon from '@mui/icons-material/LockOutlined';

const style = {
    root: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        margin: "64px 128px"
    },
    imageContainer: {
        display: 'flex',
        alignItems: 'center',
        background: "linear-gradient(45deg, rgb(17 69 147) 0%, rgb(44 87 158) 35%, rgb(73 109 171) 100%);"
    },
    image: {
        width: "100%"
    }
}

const Register = (props) => {

    const classes = useStyles(style);
    const navigate = useNavigate();

    const aleadyAccountHandler = (e) => {
        navigate("/home");
    }

    return(<>

                <Box className={classes.root}>
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Register
                    </Typography>

                    <RegisterPage></RegisterPage>
                    <Grid item>


                    <Box onClick={aleadyAccountHandler}
                            sx={{
                                cursor: 'pointer',
                                textDecoration: 'underline',
                                textDecorationColor: '#1976d2',
                            }}>
                            <Typography variant="body2"  sx={{color: '#1976d2'}}>
                                {"Already have an account? Log In"}
                            </Typography>
                        </Box>
                    </Grid>

                    
                </Box>
    </>)
}

export default Register;