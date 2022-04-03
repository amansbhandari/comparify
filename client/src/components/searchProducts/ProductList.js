import { Grid } from "@mui/material";
import React from "react";
import {  useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { gotItem } from "../../store/reducers/search";
import ProductCard from "./ProductCard";

const ProductList = (props) => {
  const products = useSelector((state) => state.product.search);
  const dispatch = useDispatch()
  const navigate = useNavigate();

  const handleOnClick = (itemId) => {
    dispatch(gotItem(itemId))
    navigate("compare");
  }

  return (
    <>
      {products.length > 0 && (
        <div>
          <Grid container spacing={3}>
          {products.map((product) => {
            return (
              <Grid item sx={{cursor: "pointer"}} onClick={() => handleOnClick(product.itemId)} >
                <ProductCard key={product.recordId} data={product}  />
              </Grid>
            );
          })}
          </Grid>
        </div>
      )}
    </>
  );
};

export default ProductList;
