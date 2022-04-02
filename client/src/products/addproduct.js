import React from "react"
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { TextField, makeStyles} from "@material-ui/core";
import { useState} from "react";
import ProductListDropDown from './ProductListDropDown'
import StoreListDropDown from "./StoreListDropDown";
import BrandListDropDown from "./BrandListDropDown";
 import ImageUploader from "../image/ImageUploader";
 import { addproducts } from "../store/thunk/userThunkCreators";

import ItemCategories from "./ItemCategories";


const Addproduct = () =>{
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
    const [product,setProduct] = useState("");
    const [brand, setBrand] = useState("");
    const[store, setStore]=useState("");
    const [volume,setVolume] = useState("");
    const[date, setDate]=useState("");
    const [price, setPrice] = useState("");
    const[discount, setDiscount]= useState("");
    const[image]=useState("");
    
  
   function handleSubmit(event) {
     addproducts({
    
      "userId": localStorage.getItem('user-id'), "product":itemCategory, "productId":product, "brandId":brand, "storeId":store, "unit":volume,"dateOfPurchase": date,"price":price, discount,
        "imageText":image, "priceAfterDiscount": (100-discount)*price/100
     })
    
   }    


    function handleCategory(itemVal){
      setCategory(itemVal);
  }

    function handleProduct(productVal){
        setProduct(productVal);
    }
    
    function handleStore(storeVal){
        setStore(storeVal);
    }

    function handleBrand(brandVal){
      setBrand(brandVal);
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
      <ProductListDropDown onProductSelection={handleProduct}/>
      </div>
        
       <div>
      <Box sx={{ m: 3}}></Box> 
      <BrandListDropDown onBrandSelection={handleBrand}/>
      </div> 

      <div>
      <Box sx={{ m: 3}}></Box> 
         <StoreListDropDown onStoreSelection={handleStore}/>
      </div> 

      <div>
      <Box sx={{ m: 3}}></Box> 
      <TextField id="volume" label="Volume (in kg)" variant="outlined"  type="number" value={volume} fullWidth={170} onChange={(e) => setVolume(e.target.value)}
        />
        </div>
      <div>
      <Box sx={{ m: 3}}></Box> 
       <TextField
        id="date"
        variant="outlined"
        type="date"
        fullWidth={170} onChange={(e) => setDate(e.target.value)}

      />
    </div>
    
      <div>
      <Box sx={{ m: 3}}></Box> 
        <TextField id="price" label="Price" variant="outlined"   type="number" value={price} fullWidth={170} onChange={(e) => setPrice(e.target.value)}
         />
      </div> 

      <div>
      <Box sx={{ m: 3}}></Box> 
        <TextField id="discount" label="Discount" variant="outlined"  type="number" value={discount} fullWidth={170} onChange={(e) => setDiscount(e.target.value)}
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
   
)}
 
export default Addproduct;