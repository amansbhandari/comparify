import React from "react"
import Box from '@mui/material/Box';
import { Typography } from "@material-ui/core";
import { TextField, makeStyles} from "@material-ui/core";
import { useState } from "react";
import { addfeedback } from "../../store/thunk/userThunkCreators";
import { Avatar } from "@mui/material";
import logo from '../../assets/logo/logo-512-trans.png';
import Grid from '@mui/material/Grid';
import { Button} from "@material-ui/core";


const Feedback = () =>{
  const useStyles = makeStyles({
    root: {
      display: 'flex',
      flexDirection: 'column',
      marginTop: "30px",
      marginLeft:"200px",
      marginRight:"200px",
      
      backgroundImage : `url(${logo})`,
       
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
  
buttonContainer: {
    display: "flex",
    justifyContent: "end"
},
field:{
  alignItems: 'center',
  flexDirection: 'column',
  marginTop: "25px",
  marginBottom: "25px",
  width: "70%",
},
  grid:{
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      margin: "64px 128px" ,
      justifyContent: 'center',
      marginLeft: '25%'  
  },
    card: {
      maxWidth: "40%",
      minHeight: "20vh",
      display: "flex",
      alignItems: "center"
    }
  });

    
    const [feedback, setFeedback] = useState("");
    const [suggestions, setSuggestions] = useState("");

    
  function handleSubmit(event) {
        event.preventDefault();
        addfeedback({
        "usersFeedback": feedback, "suggestions": suggestions 
        })

      
        }
    const classes = useStyles();
    
return(

<Box className={classes.root} >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' , alignSelf:"center"}}>
                    </Avatar>
                    <Typography component="h1" variant="h5" align="center">
                        Add Feedback
                    </Typography>
    <form onSubmit={handleSubmit}>
      
      <div>
        <Box sx={{ m: 3}} /> 
        <TextField id="feedback" label="Feedback" variant="outlined" value={feedback}  fullWidth={100} onChange={(e) => setFeedback(e.target.value)}
        />
      </div>
      <div>
  
        <Box sx={{ m: 3}} />  
        <TextField id="suggestions" label="Suggestions" variant="outlined" value={suggestions} multiline={true}  fullWidth={100} rows={5} onChange={(e) => setSuggestions(e.target.value)}
         />
      </div> 
      
<Box sx={{ m: 3}}></Box>
    <Grid item className={classes.buttonContainer} >
                        <Button color="primary" variant="contained" type="submit" className={classes.button}> Submit
    </Button>
                    </Grid>
                    </form>


                    
       </Box>

)}
export default Feedback;