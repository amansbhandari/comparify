import React, { useEffect, useState } from 'react';
import Highcharts from 'highcharts';
import HighchartsReact from 'highcharts-react-official';
import { useDispatch, useSelector } from 'react-redux';
import { Box } from '@mui/system';
import { useFormik } from 'formik';
import { Button, Grid, MenuItem, TextField, Typography } from '@mui/material';
import useStyles from "../../hooks/use-styles";
import TextSnippetIcon from '@mui/icons-material/TextSnippet';
import { fetchMonthlyTotalPurchaseOfItemCategory } from '../../store/thunk/analyticsThunkCreators';

const style = {
    buttonContainer: {
        margin: "15px 0px 0px 0px",
        display: "flex",
        alignItems: "center"
    }

};


function generateOptions(categories, series) {

    if(categories.length === 0 || series.length === 0){
        return;
    }

    return {
        chart: {
            type: 'column',
            height: "500px"
        },
        title: {
            text: ''
        },
        xAxis: {
            categories: categories,
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Rainfall (mm)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: series
    }
}

const MonthlyTotalPurchaseOfItemCategory = (props) => {

    const classes = useStyles(style);

    const dispatch = useDispatch();
    const totalPurchaseOfItemCategory = useSelector((state) => 
                state.analytics.monthly_total_purchase_of_item_category);

    const [categoryData, setCategoryData] = useState(null)

    const months = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]



    const formik = useFormik({
        initialValues: {
            month: ""
        },
        onSubmit: (values, actions) => {
            if (values.month) {
                dispatch(fetchMonthlyTotalPurchaseOfItemCategory(parseInt(values.month)))
            }
        }
    });

    const currentMonth = parseInt(formik.values.month === "" ? -1 : formik.values.month);

    useEffect(() => {
        if(currentMonth < 0){
            return;
        }

        const monthsNames = [ "January", "February", "March", "April", "May", "June", 
        "July", "August", "September", "October", "November", "December" ];

        const data = Object.entries(totalPurchaseOfItemCategory)
                        .map(itr => { return {name: itr[0], data: [itr[1]]}})

        setCategoryData(generateOptions([[monthsNames[currentMonth]]], data))
    }, [totalPurchaseOfItemCategory, currentMonth])



    return (<Box sx={{margin: "3% 0px 5% 0px"}}>
        <Typography variant='h4' sx={{textAlign: "center", marginBottom: "20px"}}>
            {"Monthly purchase of Item Category"}</Typography>
        <Box>
            <form onSubmit={formik.handleSubmit}>
                <Grid container justifyContent="center" spacing={2}>
                    <Grid item xs={3}>
                    <TextField fullWidth margin="normal"
                            select
                            variant="outlined"
                            id="month"
                            name="month"
                            label="Month"
                            autoComplete="off"
                            value={formik.values.month}
                            onChange={formik.handleChange}
                            error={formik.touched.month && Boolean(formik.errors.month)}
                            helperText={formik.touched.month && formik.errors.month}
                            inputProps={{
                                autoComplete: 'off'
                            }}>
                            {months.map(month => <MenuItem key={month}
                                value={month}>{month}</MenuItem>)}
                        </TextField>
                    </Grid>
                    <Grid item xs={3} className={classes.buttonContainer}>
                        <Button color="primary" variant="contained" type="submit" className={classes.button} >
                            Show
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </Box>

        {totalPurchaseOfItemCategory && Object.entries(totalPurchaseOfItemCategory).length > 0 &&
            <HighchartsReact highcharts={Highcharts} options={categoryData} />}
        {(!totalPurchaseOfItemCategory || Object.entries(totalPurchaseOfItemCategory).length === 0) &&
            <Box sx={{
                "display": "flex",
                "flexDirection": "column",
                "justifyContent": "center",
                "alignItems": "center",
                "height": "500px"
            }}>
                <TextSnippetIcon sx={{ color: "secondary.main", "fontSize": "200px" }} />
                <Typography variant='h5' sx={{ fontWeight: "bold" }}>No Data Found</Typography>
            </Box>}
    </Box>)
}

export default MonthlyTotalPurchaseOfItemCategory;