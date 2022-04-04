import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";

import Typography from "@mui/material/Typography";

export default function ProductCard(props) {
  const image = props.data.image;
  
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardMedia
        component="img"
        height="140"
        image={image}
        alt={props.data.productname}
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {props.data.brandName}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {props.data.description}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {props.data.storeName}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {props.data.price}
        </Typography>
      </CardContent>
    </Card>
  );
}
