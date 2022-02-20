import { Button, TextField, Box, Grid } from "@material-ui/core";
import { useFormik } from "formik";
import React from "react";


const Authentication = (props) => {

    

    const formik = useFormik({
        initialValues: {
            userIdentifier: '',
            secret: ''
        },
        onSubmit: values => {
            
        },
    });

    return (
        <Grid container justify="center">
            <Box>
                <form onSubmit={formik.handleSubmit}>
                    <Grid>
                        <Grid>
                            <TextField
                                id="userIdentifier"
                                name="userIdentifier"
                                label="Email"
                                autoComplete="off"
                                value={formik.values.userIdentifier}
                                onChange={formik.handleChange}
                                error={formik.touched.userIdentifier && Boolean(formik.errors.userIdentifier)}
                                helperText={formik.touched.userIdentifier && formik.errors.userIdentifier}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item>
                            <TextField
                                id="secret"
                                name="secret"
                                label="Secret"
                                autoComplete="off"
                                value={formik.values.secret}
                                onChange={formik.handleChange}
                                error={formik.touched.secret && Boolean(formik.errors.secret)}
                                helperText={formik.touched.secret && formik.errors.secret}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item>
                            <Button color="primary" variant="contained" type="submit">
                                Submit
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Box>
        </Grid>);
}

export default Authentication;
