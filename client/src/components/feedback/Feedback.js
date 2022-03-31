import React from "react"
import Box from '@mui/material/Box';
import { Typography } from "@material-ui/core";
import { TextField, makeStyles} from "@material-ui/core";
import { useState } from "react";
import { addfeedback } from "../../store/thunk/userThunkCreators";
import { Avatar } from "@mui/material";
import logo from '../../assets/logo/logo-512-trans.png';


const Feedback = () =>{
  const useStyles = makeStyles({
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
    card: {
      maxWidth: "40%",
      minHeight: "20vh",
      display: "flex",
      alignItems: "center"
    }
  });

    const [email, setEmail] = useState("");
    const [feedback, setFeedback] = useState("");
    const [suggestions, setSuggestions] = useState("");

    
      function handleSubmit(event) {
        event.preventdefault();
        addfeedback({
        
        "email": email, "Usersfeedback": feedback, "suggestions": suggestions 
        })

      
       
      }
    const classes = useStyles();
    
return(

<Box className={classes.root} >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Add Feedback
                    </Typography>
    <form onSubmit={handleSubmit}>
      <div>
        <TextField id="email" label="Email" variant="outlined" value={email} onChange={(e) => setEmail(e.target.value)} />
      </div>
      <div>
       
       <Box sx={{ m: 3}} /> 
        <TextField id="feedback" label="Feedback" variant="outlined" value={feedback} multiline={true} rows={3} onChange={(e) => setFeedback(e.target.value)}
        />
      </div>
      <div>
      
         <Box sx={{ m: 3}} /> 
        <TextField id="suggestions" label="Suggestions" variant="outlined" value={suggestions} multiline={true} rows={5} onChange={(e) => setSuggestions(e.target.value)}
         />
      </div> 
      
      <Box sx={{ m: 3}}></Box> 
      <button variant="contained" style={{display: "flex", justifyContent: "right", color:"primary" }} className="float-right" type="submit">Submit</button>
    </form>

                    
       </Box>

)}
export default Feedback;