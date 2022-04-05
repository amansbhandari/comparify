import React, { useEffect, useState } from 'react';
import Highcharts from 'highcharts';
import HighchartsReact from 'highcharts-react-official';
import { useDispatch, useSelector } from 'react-redux';
import { Box } from '@mui/system';
import { useFormik } from 'formik';
import { Button, Grid, TextField, Typography } from '@mui/material';
import useStyles from "../../hooks/use-styles";
import TextSnippetIcon from '@mui/icons-material/TextSnippet';
import { fetchProductCountForCategory } from '../../store/thunk/analyticsThunkCreators';

const style = {
    buttonContainer: {
        margin: "15px 0px 0px 0px",
        display: "flex",
        alignItems: "center"
    }

};


function generateOptions(data) {

    return {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie',
            height: "500px"
        },
        title: {
            text: ''
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            name: 'Items',
            colorByPoint: true,
            data: data
        }]
    }
}

const ProductCountForCategory = (props) => {

    const classes = useStyles(style);

    const dispatch = useDispatch();
    const productCountForCategory = useSelector((state) => state.analytics.product_count_for_category);

    const [categoryData, setCategoryData] = useState(null)

    const now = new Date();
    const month = now.getMonth() + 1;
    const day = now.getDate();
    const todayDate = now.getFullYear() + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day)

    const formik = useFormik({
        initialValues: {
            date: todayDate
        },
        onSubmit: (values, actions) => {
            if (values.date) {
                dispatch(fetchProductCountForCategory(values.date))
            }
        }
    });

    useEffect(() => {

        const date = productCountForCategory.map(x => {
            return {
                name: x.category,
                y: x.count
            }
        })

        setCategoryData(generateOptions(date))
    }, [productCountForCategory])



    return (<Box sx={{margin: "3% 0px 5% 0px"}}>
                <Typography variant='h4' sx={{textAlign: "center", marginBottom: "20px"}}>
            {"Percentage of Product Purchase for each Category"}</Typography>
        <Box>
            <form onSubmit={formik.handleSubmit}>
                <Grid container justifyContent="center" spacing={2}>
                    <Grid item xs={3}>
                        <TextField fullWidth margin="normal"
                            type="date"
                            variant="outlined"
                            id="date"
                            name="date"
                            label="Date"
                            placeholder=""
                            autoComplete="off"
                            value={formik.values.date}
                            onChange={formik.handleChange}
                            error={formik.touched.date && Boolean(formik.errors.date)}
                            helperText={formik.touched.date && formik.errors.date}
                            inputProps={{
                                autoComplete: 'off'
                            }}>
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

        {productCountForCategory && productCountForCategory.length > 0 &&
            <HighchartsReact highcharts={Highcharts} options={categoryData} />}
        {(!productCountForCategory || productCountForCategory.length === 0) &&
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

export default ProductCountForCategory;