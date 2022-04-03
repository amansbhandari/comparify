import { Grid } from "@mui/material";
import React from "react";
import {  useSelector } from "react-redux";
import ProductCard from "./ProductCard";

const ProductList = (props) => {
  const products = useSelector((state) => state.product.search);


  return (
    <>
      {products.length > 0 && (
        <div>
          <Grid container spacing={3}>
          {products.map((product) => {
            return (
              <ProductCard key={product.recordId} data={product} />
            );
          })}
          </Grid>
        </div>
      )}
    </>
  );
};

export default ProductList;
