import React from "react"
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { TextField, makeStyles} from "@material-ui/core";
import { useState} from "react";
import { addAdminproducts } from "../../store/thunk/userThunkCreators";
import ImageUploader from "../image/ImageUploader";

import ItemCategories from "./ItemCategories";


const AddAdminproduct = () =>{
  const useStyles = makeStyles({
    root: {
      background: "linear-gradient",
      minWidth: "100%",
      minHeight: "100vh",
      display: "flex",
      flexDirection: "column",
      justifyContent: "center"
    },
    card: {
      maxWidth: "40%",
      minHeight: "20vh",
      display: "flex",
      alignItems: "center"
    },
    select: {
        background: 'transparent'
    }
 });


  
const classes = useStyles();
 

    const[itemCategory, setCategory]=useState("");
    const [productName,setProductName] = useState("");
    const [description, setDescription] = useState("");
    const[image]=useState("");
    
  
   function handleSubmit(event) {
    addAdminproducts({
    
    "name": productName, "itemCategoryId":itemCategory,  "default_image": image, "description":description
      
     })
    
   }    
  

  function handleCategory(itemVal){
    setCategory(itemVal);
}



 

return(
    
    <Grid
    className={classes.root}
      spacing={0}
      alignItems="center"
      justify="center"
      >
        
  <form onSubmit={handleSubmit}>
  
     <div>
    <Box sx={{ m: 3}}></Box> 
    <ItemCategories onCategorySelection={handleCategory}/>
    </div>

      <div>
      <Box sx={{ m: 3}}></Box> 
        <TextField id="productName" label="Product Name" variant="outlined"  value={productName} fullWidth={170} onChange={(e) => setProductName(e.target.value)}
         />
      </div> 

      <div>
      <Box sx={{ m: 3}}></Box> 
        <TextField id="description" label="Description" variant="outlined"  value={description} fullWidth={170} onChange={(e) => setDescription(e.target.value)}
         />
      </div> 

      <div>
       <Box sx={{ m: 3}}></Box>  
       <ImageUploader onImageSelection={handleSubmit}/>
      </div>


    <Box sx={{ m: 3}}></Box> 
      <button variant="contained" style={{display: "flex", justifyContent: "right", color:"primary" }} className="float-right" type="submit">Submit</button>
    </form>
    
    </Grid>
   
)
}
export default AddAdminproduct;