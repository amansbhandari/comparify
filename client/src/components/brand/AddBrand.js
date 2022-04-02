import { Button, TextField, Box, Grid } from "@material-ui/core";
import { useFormik } from "formik";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import logo from "../../assets/logo/logo-512-trans.png";

import useStyles from "../../hooks/use-styles";
import Alert from "@mui/material/Alert";
import AlertTitle from "@mui/material/AlertTitle";
import { saveBrand } from "../../store/thunk/brandThunkCreator";
import { brandAddSuccess } from "../../store/reducers/saveBrand";

const style = {
  root: {
    display: "inline-block",
    margin: "30px 0px",
    backgroundImage: `url(${logo})`,
  },
  button: {
    marginTop: "16px",
    marginBottom: "8px",
  },
  buttonContainer: {
    display: "flex",
    justifyContent: "end",
  },
  form: {
    width: "50%",
    margin: "auto",
  },
};

const AddBrand = (props) => {
  const classes = useStyles(style);
  const dispatch = useDispatch();
  var { status } = useSelector((state) => state.saveBrand.response);

  const formik = useFormik({
    initialValues: {
      name: "",
      description: "",
    },
    onSubmit: (values) => {
      const { name, description } = values;

      if (!name || !description) {
        return;
      }

      dispatch(
        saveBrand({
          name,
          description,
        })
      );
    },
  });

  useEffect(() => {
    dispatch(brandAddSuccess({ status: "not created" }));
  }, [dispatch]);

  function showSuccess() {
    if (status === "Created") {
      return (
        <Alert severity="success">
          <AlertTitle>Success</AlertTitle>
          Store added successfully
        </Alert>
      );
    }
  }

  return (
    <Grid container className={classes.root}>
      <Box>
        <form onSubmit={formik.handleSubmit} className={classes.form}>
          <Grid item>
            <TextField
              fullWidth
              margin="normal"
              variant="outlined"
              id="name"
              name="name"
              label="Brand Name"
              autoComplete="off"
              value={formik.values.name}
              onChange={formik.handleChange}
              error={formik.touched.name && Boolean(formik.errors.name)}
              helperText={formik.touched.name && formik.errors.name}
              inputProps={{
                autoComplete: "off",
              }}
            />
          </Grid>
          <Grid item>
            <TextField
              fullWidth
              margin="normal"
              variant="outlined"
              id="description"
              name="description"
              label="Brand Description"
              autoComplete="off"
              value={formik.values.description}
              onChange={formik.handleChange}
              error={
                formik.touched.description && Boolean(formik.errors.description)
              }
              helperText={
                formik.touched.description && formik.errors.description
              }
              inputProps={{
                autoComplete: "off",
              }}
            />
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
        </form>
      </Box>

      {showSuccess()}
    </Grid>
  );
};

export default AddBrand;
