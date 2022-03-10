import { Box } from "@material-ui/core";
import React from "react";
import useStyles from "../../hooks/use-styles";


import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';
import Paper from '@mui/material/Paper';


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

const UserProfile = (props) => {

    const classes = useStyles(style);

    return(<>
        <Grid item xs={12} sm={7} md={6} component={Paper} elevation={6} square>
                <Box className={classes.root}>
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        User Profile
                    </Typography>


                    
                </Box>
            </Grid>
    </>)
}

export default UserProfile;