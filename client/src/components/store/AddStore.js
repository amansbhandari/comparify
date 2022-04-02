import { Button, TextField, Box, Grid } from "@material-ui/core";
import { useFormik } from "formik";
import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import logo from '../../assets/logo/logo-512-trans.png';
import { saveStore } from "../../store/thunk/storeThunkCreators";

import useStyles from "../../hooks/use-styles";

const style = {
    root: {
        display: "inline-block",
        margin: "30px 0px",
        backgroundImage : `url(${logo})`,
    },
    button: {
        marginTop: "16px",
        marginBottom: "8px"
    },
    buttonContainer: {
        display: "flex",
        justifyContent: "end"
    },
    form: {
        width : '50%',
        margin: "auto"
    }
};


const AddStore = (props) => {

    const classes = useStyles(style);
    const dispatch = useDispatch();

    const formik = useFormik({
        initialValues: {
            userIdentifier: '', secret: ''
        },
        onSubmit: (values, actions) => {

            const { storeName, streetName, city, phone } = values;

            if(!storeName || !streetName || !city || !phone){
                return;
            }
            

            dispatch(saveStore({
                storeName, streetName, city, phone
            }));

            resetForm("")
        }
    });

    const { resetForm } = formik;

    useEffect(() => {
    }, [])

    return (
        <Grid container className={classes.root}>
            <Box>
                <form onSubmit={formik.handleSubmit} className={classes.form} >
                    <Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                variant="outlined" 
                                id="storeName"
                                name="storeName"
                                label="Store Name"
                                autoComplete="off"
                                value={formik.values.storeName}
                                onChange={formik.handleChange}
                                error={formik.touched.storeName && Boolean(formik.errors.storeName)}
                                helperText={formik.touched.storeName && formik.errors.storeName}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                variant="outlined" 
                                id="streetName"
                                name="streetName"
                                label="Street Name"
                                autoComplete="off"
                                value={formik.values.streetName}
                                onChange={formik.handleChange}
                                error={formik.touched.streetName && Boolean(formik.errors.streetName)}
                                helperText={formik.touched.streetName && formik.errors.streetName}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                variant="outlined" 
                                id="city"
                                name="city"
                                label="City"
                                autoComplete="off"
                                value={formik.values.city}
                                onChange={formik.handleChange}
                                error={formik.touched.city && Boolean(formik.errors.city)}
                                helperText={formik.touched.city && formik.errors.city}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                variant="outlined" 
                                id="phone"
                                name="phone"
                                label="Phone Number"
                                autoComplete="off"
                                value={formik.values.phone}
                                onChange={formik.handleChange}
                                error={formik.touched.phone && Boolean(formik.errors.phone)}
                                helperText={formik.touched.phone && formik.errors.phone}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item className={classes.buttonContainer}>
                            <Button color="primary" variant="contained" type="submit" className={classes.button} >
                                Submit
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Box>
        </Grid>);
}

export default AddStore;
