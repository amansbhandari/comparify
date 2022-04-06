import { Box, Button, Grid, MenuItem, TextField } from "@mui/material";
import { useFormik } from "formik";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import useStyles from "../../hooks/use-styles";
import { createAlert } from "../../store/thunk/alertThunkCreators";
import { getBrands } from "../../store/thunk/brandThunkCreator";
import { getItems } from "../../store/thunk/itemThunkCreator";

const style = {
    root: {
        "display": "flex",
        "justify-content": "center"
    },
    titleContainer: {
        "display": "flex",
        "justify-content": "center",
        margin: "15px"
    },
    buttonContainer: {
        margin: "15px 0px 0px 0px"
    }
};

const CreateAlert = (props) => {

    const classes = useStyles(style);
    const dispatch = useDispatch();
    const [formSubmit, handleFormSubmit] = useState(false)

    useEffect(() => {
        dispatch(getItems())
        dispatch(getBrands())
    }, [dispatch])


    const recentAlertCreated = useSelector((state) => state.alert.recent_alert_created)

    const items = useSelector((state) => state.item.list).map(item => {
        return {
            id: {
                entity_id: item.id,
                entity_type: "item"
            },
            name: item.itemName
        }
    });
    const brands = useSelector((state) => state.brand.list).map(brand => {
        return {
            id: {
                entity_id: brand.id,
                entity_type: "brand"
            },
            name: brand.name
        }
    })


    const formik = useFormik({
        initialValues: {
            alertIdentifier: "",
            item: "",
            brand: "",
            alertType: "",
            min: 0,
            max: 0
        },
        onSubmit: (values, actions) => {

            const { alertIdentifier, item, brand,
                alertType: type, minValue: min, maxValue: max } = values;

            if (!(alertIdentifier && item && brand && type)) {
                return;
            }

            let data = {
                "alert_identifier": alertIdentifier,
                item: JSON.parse(item),
                brand: JSON.parse(brand),
                type
            }

            if (type === "PRICE_RANGE") {
                data["price_range"] = { min, max }
            }

            dispatch(createAlert(data));
            handleFormSubmit(true);
        }
    });

    const { resetForm } = formik;

    useEffect(() => {

        if (formSubmit && recentAlertCreated.status === "Created") {
            resetForm({
                alertIdentifier: "",
                item: "",
                brand: "",
                alertType: "",
                min: 0,
                max: 0
            })

            handleFormSubmit(false);
        }


    }, [formSubmit, recentAlertCreated, resetForm, handleFormSubmit])

    const types = [{
        id: "PRICE_DROP",
        name: "Price Drop"
    },
    {
        id: "PRICE_RANGE",
        name: "Price Range"
    },
    {
        id: "PRODUCT_INFORMATION_AVAILABLE",
        name: "Product Information Available"
    }]

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
                                {items.map(item => <MenuItem key={JSON.stringify(item.id)}
                                    value={JSON.stringify(item.id)}>{item.name}</MenuItem>)}
                            </TextField>
                        </Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal"
                                select
                                variant="outlined"
                                id="brand"
                                name="brand"
                                label="Brand"
                                autoComplete="off"
                                value={formik.values.brand}
                                onChange={formik.handleChange}
                                error={formik.touched.brand && Boolean(formik.errors.brand)}
                                helperText={formik.touched.brand && formik.errors.brand}
                                inputProps={{
                                    autoComplete: 'off'
                                }}>
                                {brands.map(brand => <MenuItem key={JSON.stringify(brand.id)}
                                    value={JSON.stringify(brand.id)}>{brand.name}</MenuItem>)}
                            </TextField>
                        </Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal"
                                select
                                variant="outlined"
                                id="alertType"
                                name="alertType"
                                label="Alert Type"
                                autoComplete="off"
                                value={formik.values.alertType}
                                onChange={formik.handleChange}
                                error={formik.touched.alertType && Boolean(formik.errors.alertType)}
                                helperText={formik.touched.alertType && formik.errors.alertType}
                                inputProps={{
                                    autoComplete: 'off'
                                }}>
                                {types.map(type => <MenuItem key={type.id}
                                    value={type.id}>{type.name}</MenuItem>)}
                            </TextField>
                        </Grid>
                        {formik.values.alertType === "PRICE_RANGE" && (<Grid item>
                            <TextField fullWidth margin="normal"
                                variant="outlined"
                                id="minValue"
                                name="minValue"
                                label="Minimum Price"
                                autoComplete="off"
                                value={formik.values.minValue}
                                onChange={formik.handleChange}
                                error={formik.touched.minValue && Boolean(formik.errors.minValue)}
                                helperText={formik.touched.minValue && formik.errors.minValue}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>)}
                        {formik.values.alertType === "PRICE_RANGE" && (<Grid item>
                            <TextField fullWidth margin="normal"
                                variant="outlined"
                                id="maxValue"
                                name="maxValue"
                                label="Maximum Price"
                                autoComplete="off"
                                value={formik.values.maxValue}
                                onChange={formik.handleChange}
                                error={formik.touched.maxValue && Boolean(formik.errors.maxValue)}
                                helperText={formik.touched.maxValue && formik.errors.maxValue}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>)}
                        <Grid item className={classes.buttonContainer}>
                            <Button color="primary" variant="contained" type="submit" className={classes.button} >
                                Create
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Box>
        </Grid>)
}

export default CreateAlert;