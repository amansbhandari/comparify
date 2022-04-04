import { Button, TextField, Box, Grid} from "@material-ui/core";
import { useFormik } from "formik";
import React from "react";
import { useDispatch} from "react-redux";
import useStyles from "../../hooks/use-styles";
import { searchProduct } from "../../store/thunk/productThunkCreators";



const style = {
  root: {
    display: "inline-block",
    margin: "30px 0px",
  },
  button: {
    marginTop: "16px",
    marginBottom: "8px",
  },
  buttonContainer: {
    display: "flex",
    justifyContent: "end",
  },
};
const SearchBar = (props) => {
  const classes = useStyles(style);
  const dispatch = useDispatch();

  const formik = useFormik({
    initialValues: {},
    onSubmit: (values, actions) => {
      const { name } = values;
      dispatch(searchProduct(name));
    },
  });
 


  return (
    <Grid container className={classes.root}>
      <Box>
        <form onSubmit={formik.handleSubmit}>
          <Grid>
            <Grid item>
              <TextField
                fullWidth
                margin="normal"
                variant="outlined"
                id="name"
                name="name"
                label="Product Name"
                autoComplete="off"
                value={formik.values.name}
                onChange={formik.handleChange}
                error={formik.touched.name && Boolean(formik.errors.name)}
                helperText={formik.touched.name && formik.errors.name}
                inputProps={{
                  autoComplete: "off",
                }}
              ></TextField>
            </Grid>

            <Grid item className={classes.buttonContainer}>
              <Button
                color="primary"
                variant="contained"
                type="submit"
                className={classes.button}
              >
                Submit
              </Button>
            </Grid>
          </Grid>
        </form>
      </Box>
    </Grid> 
  
  
  );
};
export default SearchBar;
