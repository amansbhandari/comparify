import { Box,Button,TextField} from "@material-ui/core";
import React,{ useEffect } from "react";
import useStyles from "../../hooks/use-styles";

import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';
import logo from '../../assets/logo/logo-512-trans.png';
import goldBadge from '../../assets/images/goldbadge.png';
import silverBadge from '../../assets/images/silverbadge.png';
import notificationAllowed from '../../assets/images/notificationAllowed.png';
import orderMore from '../../assets/images/orderMore.png';

import { getDetails, saveDetails } from "../../store/thunk/userThunkCreators";


const style = {
    root: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        marginTop: "30px",
        backgroundImage : `url(${logo})`,
        width: '100%',  
        height: '100%',
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
        fontSize: 18,
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
        marginTop: "25px",
        marginBottom: "25px",
        width: "70%",
    },
    label:{
        marginLeft: '30%',
        fontSize: 16,
        color: 'gray'
    },

    button: {
        marginTop: "16px",
        marginBottom: "8px"
    },
    buttonContainer: {
        display: "flex",
        justifyContent: "end"
    },
    badgeStyle: {
        
        position: 'absolute', 
        top: 80,
        right: 120,
        width: "110px"
    },

    cartoon: {
        
        position: 'absolute', 
        top: 350,
        right: 20,
        width: "320px"
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
        
        getDetails(localStorage.getItem('user-id')).then((data) => {
            localStorage.setItem("email", data.email);
            localStorage.setItem("firstName", data.firstName);
            localStorage.setItem("lastName", data.lastName);
            localStorage.setItem("username", data.username);
            localStorage.setItem("points", data.points)
            localStorage.setItem("member", data.type)

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

    function username()
    {
        return localStorage.getItem("username");
    }

    function userPoints()
    {
        return localStorage.getItem("points");
    }

    function userMember()
    {
        return localStorage.getItem("member");
    }

    function badgeImage()
    {
        if(localStorage.getItem("member")  === 'gold')
        {
            return goldBadge
        }
        else
        {
            return silverBadge
        }
    }

    function cartoonImage()
    {
        if(localStorage.getItem("member")  === 'gold')
        {
            return notificationAllowed
        }
        else
        {
            return orderMore
        }
    }

    return(<>
                <Box className={classes.root} >
                    <img src = {badgeImage()} style = {style.badgeStyle} alt=""/>
                    <img src = {cartoonImage()} style = {style.cartoon} alt=""/>
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        {username()}
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

                    <div style={style.field}>
                        <label style= {style.label}>Points</label>
                        {showPoints()}
                    </div>

                    <div style={style.field}>
                        <label style= {style.label}>Member</label>
                        {showMember()}
                    </div>

                    <Grid item className={classes.buttonContainer} >
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
            "username": localStorage.getItem('user-id'), email, firstName,lastName
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

function showPoints()
{
        return <label style= {style.editableField}>{userPoints()}</label>
}

function showMember()
{   
    return <label style= {style.editableField}>{userMember()}</label>
}

}

export default UserProfile;