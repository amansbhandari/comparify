import { Box, Button, Grid, MenuItem, TextField } from "@mui/material";
import { useFormik } from "formik";
import React from "react";

import useStyles from "../../hooks/use-styles";

const style = {
    root: {
    }
};


const Alert = (props) => {

    const classes = useStyles(style);

    const formik = useFormik({
        initialValues: {
            alertIdentifier: "",
            item: "",
            brand: ""
        },
    });

    return (
        <Grid container className={classes.root}>
            <Box>
                <form onSubmit={formik.handleSubmit}>
                    <Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                variant="outlined" 
                                id="alertIdentifier"
                                name="alertIdentifier"
                                label="Alert Identifier"
                                autoComplete="off"
                                value={formik.values.alertIdentifier}
                                onChange={formik.handleChange}
                                error={formik.touched.alertIdentifier && Boolean(formik.errors.alertIdentifier)}
                                helperText={formik.touched.alertIdentifier && formik.errors.alertIdentifier}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                select
                                variant="outlined" 
                                id="item"
                                name="item"
                                label="Item"
                                autoComplete="off"
                                value={formik.values.item}
                                onChange={formik.handleChange}
                                error={formik.touched.item && Boolean(formik.errors.item)}
                                helperText={formik.touched.item && formik.errors.item}
                                inputProps={{
                                    autoComplete: 'off'
                                }}>
                                <MenuItem value={10}>Ten</MenuItem>
                                <MenuItem value={20}>Twenty</MenuItem>
                                <MenuItem value={30}>Thirty</MenuItem>
                            </TextField>
                        </Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                select
                                variant="outlined" 
                                id="brand"
                                name="brand"
                                label="brand"
                                autoComplete="off"
                                value={formik.values.brand}
                                onChange={formik.handleChange}
                                error={formik.touched.brand && Boolean(formik.errors.brand)}
                                helperText={formik.touched.brand && formik.errors.brand}
                                inputProps={{
                                    autoComplete: 'off'
                                }}>
                                <MenuItem value={10}>Ten</MenuItem>
                                <MenuItem value={20}>Twenty</MenuItem>
                                <MenuItem value={30}>Thirty</MenuItem>
                            </TextField>
                        </Grid>
                        <Grid item className={classes.buttonContainer}>
                            <Button color="primary" variant="contained" type="submit" className={classes.button} >
                                Submit
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Box>
        </Grid>)

}

export default Alert;