import { Box,Button,TextField} from "@material-ui/core";
import React from "react";
import useStyles from "../../hooks/use-styles";

import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';


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
    // const [task, setTask] = useState("");
    const [isEditMode, setEditMode] = React.useState(false);

    function userEmail()
    {
        return "amansbhandari@gmail.com";
    }

    function userFirstName()
    {
        return "Aman Singh";
    }

    function userLastName()
    {
        return "Bhandari";
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
                        <label style= {style.label}>First name</label>
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
    setEditMode(true)
}

function showEmailId()
{
    if(isEditMode)
    {
        return <TextField id="outlined-basic" label="Email Id" variant="outlined" style= {style.editableField} disabled={!isEditMode}/>
    }
    else{
        return <label style= {style.editableField} disabled={isEditMode}>{userEmail()}</label>
    }
}

function showFirstName()
{
    if(isEditMode)
    {
        return <TextField id="outlined-basic" label="First name" variant="outlined" style= {style.editableField} disabled={!isEditMode}/>
    }
    else{
        return <label style= {style.editableField} disabled={isEditMode}>{userFirstName()}</label>
    }
}

function showLastName()
{
    if(isEditMode)
    {
        return <TextField id="outlined-basic" label="Last name" variant="outlined" style= {style.editableField} disabled={!isEditMode} />
    }
    else{
        return <label style= {style.editableField} disabled={isEditMode}>{userLastName()}</label>
    }
}
}

export default UserProfile;