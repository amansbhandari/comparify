import { TextField } from "@material-ui/core";
import React, { useState } from "react";
 import httpClient from "../../store/thunk/interceptor"


export default function ImageUploader(){
  
   const [postImage, setPostImage] = useState({
    myFile: "",
    });

const createImage = (newImage) => httpClient.post(newImage);

  const createPost = async (post) => {
    try {
      await createImage(post);
    } catch (error) {
      console.log(error.message);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    createPost(postImage);
   this.props.onImageSelection(e.target.myFile); 
  };

  const convertToBase64 = (file) => {
    return new Promise((resolve, reject) => {
      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);
      fileReader.onload = () => {
        resolve(fileReader.result);
      };
      fileReader.onerror = (error) => {
        reject(error);
      };
    });
  };
  const handleFileUpload = async (e) => {
    const file = e.target.files[0];
    const base64 = await convertToBase64(file);
    setPostImage({ ...postImage, myFile: base64 });
  };

  return (
    <div>
      <label for="pictureInput"><strong>Add Invoice</strong></label>
        <TextField
         onSubmit={handleSubmit}
          type="file"
           id="pictureInput"
          fullWidth={170}
          name="myFile"
          accept=".jpeg, .png, .jpg"
          onChange={(e) => handleFileUpload(e)}
      
        />
      
   </div>
  );
}