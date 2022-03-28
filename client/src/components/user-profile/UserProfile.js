import { Box,Button,TextField} from "@material-ui/core";
import React,{ useEffect } from "react";
import useStyles from "../../hooks/use-styles";

import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';

import { getDetails, saveDetails } from "../../store/thunk/userThunkCreators";


const style = {
    root: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        margin: "64px 128px",
        marginTop: "80px",
        
    },
    imageContainer: {
        display: 'flex',
        alignItems: 'center',
        background: "linear-gradient(45deg, rgb(17 69 147) 0%, rgb(44 87 158) 35%, rgb(73 109 171) 100%);"
    },
    image: {
        width: "100%"
    },
    editableField: {
        width : '50%',
        float : 'right',
        fontSize: 24,
    },
    grid:{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        margin: "64px 128px" ,
        justifyContent: 'center',
        marginLeft: '25%'  
    },
    field:{
        alignItems: 'center',
        flexDirection: 'column',
        marginTop: "50px",
        marginBottom: "50px",
        width: "70%",
    },
    label:{
        marginLeft: '30%',
        fontSize: 22,
        color: 'gray'
    },

    button: {
        marginTop: "16px",
        marginBottom: "8px"
    },
    buttonContainer: {
        display: "flex",
        justifyContent: "end"
    }
}

const UserProfile = (props) => {

    const classes = useStyles(style);
    const [isEditMode, setEditMode] = React.useState(false);
    const [isDataLoaded, setDataLoaded] = React.useState(false);
    
    var email = ""
    var firstName = ""
    var lastName = ""

    useEffect(() => {
        getDetails(localStorage.getItem('user_identifier')).then((data) => {
            localStorage.setItem("email", data.email);
            localStorage.setItem("firstName", data.firstName);
            localStorage.setItem("lastName", data.lastName);
            if(isDataLoaded === false)
                setDataLoaded(true);
         })
         .catch(err => console.log("Axios err: ", err))
    }, [isDataLoaded])

    function userEmail()
    {
        return localStorage.getItem("email");
    }

    function userFirstName()
    {
        return localStorage.getItem("firstName");
    }

    function userLastName()
    {
        return localStorage.getItem("lastName");
    }

    return(<>
                <Box className={classes.root}>
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        User Profile
                    </Typography>
                    <div style={style.field}>
                        <label style= {style.label}>Email Id</label>
                        {showEmailId()}
                    </div>
                    
                    <div style={style.field}>
                        <label style= {style.label}>First Name</label>
                        {showFirstName()}
                    </div>
                     
                    <div style={style.field}>
                        <label style= {style.label}>Last Name</label>
                        {showLastName()}
                    </div>

                    <Grid item className={classes.buttonContainer}>
                        <Button color="primary" variant="contained" type="submit" className={classes.button} onClick={() => {
            editClicked()}}>
                            {getButtonLabel()}
                        </Button>
                    </Grid>
                </Box>
    </>)

function getButtonLabel()
{
    if(isEditMode)
    {
        return 'Save';
    }
    return 'Edit';
}
function editClicked()
{
    if(isEditMode === false)        //Click to edit
    {
        setEditMode(true)
        if(isDataLoaded === true)
            setDataLoaded(false);
    }
    else                            //Save the user details
    {
        saveDetails({
            "username": localStorage.getItem('user_identifier'), email, firstName,lastName
        }).then((data) => {
            localStorage.setItem("email", data.email);
            localStorage.setItem("firstName", data.firstName);
            localStorage.setItem("lastName", data.lastName);
            
            if(isDataLoaded === true)
                setDataLoaded(false);

         })
        setEditMode(false);

    }
    
}

function emailChanged(e)
{
    email = e.target.value
}

function firstNameChanged(e)
{
    firstName = e.target.value
}

function lastNameChanged(e)
{
    lastName = e.target.value
}

function showEmailId()
{
    if(isEditMode)
    {
        return <TextField id="email-text" label="Email Id" variant="outlined" style= {style.editableField} disabled={!isEditMode} onChange={(e) => {
            emailChanged(e)}}/>
    }
    else{
        return <label style= {style.editableField} disabled={isEditMode}>{userEmail()}</label>
    }
}

function showFirstName()
{
    if(isEditMode)
    {
        return <TextField id="firstName-text" label="First name" variant="outlined" style= {style.editableField} disabled={!isEditMode} onChange={(e) => {
            firstNameChanged(e)}}/>
    }
    else{
        return <label style= {style.editableField} disabled={isEditMode}>{userFirstName()}</label>
    }
}

function showLastName()
{
    if(isEditMode)
    {
        return <TextField id="lastName-text" label="Last name" variant="outlined" style= {style.editableField} disabled={!isEditMode} onChange={(e) => {
            lastNameChanged(e)}}/>
    }
    else{
        return <label style= {style.editableField} disabled={isEditMode}>{userLastName()}</label>
    }
}
}

export default UserProfile;