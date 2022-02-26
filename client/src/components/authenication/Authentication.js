import { Button, TextField, Box, Grid } from "@material-ui/core";
import { useFormik } from "formik";
import React from "react";
import { useDispatch } from "react-redux";
import { authenication } from "../../store/thunk/userThunkCreators";

import useStyles from "../../hooks/use-styles";

const style = {
    root: {
        display: "inline-block",
        margin: "30px 0px",
    },
    button: {
        marginTop: "16px",
        marginBottom: "8px"
    },
    buttonContainer: {
        display: "flex",
        justifyContent: "end"
    }
};


const Authentication = (props) => {

    const classes = useStyles(style);
    const dispatch = useDispatch();

    const formik = useFormik({
        initialValues: {
            userIdentifier: '',
            secret: ''
        },
        onSubmit: values => {

            const { userIdentifier, secret } = values;

            if(!userIdentifier || !secret){
                return;
            }

            dispatch(authenication({
                "user_identifier": userIdentifier, secret
            }));

        }
    });

    return (
        <Grid container className={classes.root}>
            <Box>
                <form onSubmit={formik.handleSubmit}>
                    <Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                id="userIdentifier"
                                name="userIdentifier"
                                label="User Identifier"
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
                            <TextField fullWidth margin="normal" 
                                id="secret"
                                name="secret"
                                label="Secret"
                                autoComplete="off"
                                type="password"
                                value={formik.values.secret}
                                onChange={formik.handleChange}
                                error={formik.touched.secret && Boolean(formik.errors.secret)}
                                helperText={formik.touched.secret && formik.errors.secret}
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

export default Authentication;
