import { Box,Button} from "@material-ui/core";
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
        marginTop: "150px"
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
        {/* <Grid item xs={12} sm={7} md={6} component={Paper} elevation={6} square style= {style.grid}> */}
                <Box className={classes.root}>
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        User Profile
                    </Typography>
                    <div style={style.field}>
                        <label style= {style.label}>Email Id</label>
                        <label style= {style.editableField}>{userEmail()}</label>
                        {/* <Editable text={userEmail()} placeholder="Enter email id" type="input" style={style.editableField} >
                         <input type="text" name="emailid" placeholder="Enter email id" value={task}
                            onChange={e => setTask(e.target.value)}  style={style.editableField}/>
                        </Editable> */}
                    </div>
                    
                    <div style={style.field}>
                        <label style= {style.label}>First name</label>
                        <label style= {style.editableField}>{userFirstName()}</label>
                        {/* <Editable text={userFirstName()} placeholder={"Enter first name"} type="input" style={style.editableField}>
                        <input type="text" name="emailid" placeholder="Enter first name" value={task}
                        onChange={e => setTask(e.target.value)}   style={style.editableField}/>
                        </Editable> */}
                    </div>
                     
                    <div style={style.field}>
                        <label style= {style.label}>Last Name</label>
                        <label style= {style.editableField}>{userLastName()}</label>
                        {/* <Editable text={userLastName()} placeholder={"Enter last name"} type="input" style={style.editableField}>
                            <input type="text" name="emailid" placeholder="Enter last name" value={task}
                            onChange={e => setTask(e.target.value)}   style={style.editableField}/>
                        </Editable> */}
                    </div>

                    <Grid item className={classes.buttonContainer}>
                        <Button color="primary" variant="contained" type="submit" className={classes.button} >
                            Edit
                        </Button>
                    </Grid>
                </Box>
            {/* </Grid> */}

    
    </>)
}

export default UserProfile;