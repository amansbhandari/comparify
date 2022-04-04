import React, { useEffect, useState } from 'react';
import Highcharts from 'highcharts';
import HighchartsReact from 'highcharts-react-official';
import { useDispatch, useSelector } from 'react-redux';
import { fetchPriceTrendForBrands } from '../../store/thunk/analyticsThunkCreators';
import { Box } from '@mui/system';
import { useFormik } from 'formik';
import { Button, Grid, MenuItem, TextField, Typography } from '@mui/material';
import { getItems } from '../../store/thunk/itemThunkCreator';
import useStyles from "../../hooks/use-styles";
import TextSnippetIcon from '@mui/icons-material/TextSnippet';

const style = {
    buttonContainer: {
        margin: "15px 0px 0px 0px",
        display: "flex",
        alignItems: "center"
    }
    
};


function generateOptions(title, categories, data) {

    if (categories.length === 0 || data.length === 0) {
        return null;
    }

    return {
        chart: { type: 'spline', height: "500px" },
        title: {
            text: title
        },
        yAxis: {
            title: {
                text: 'Price'
            }
        },

        xAxis: {
            title: {
                text: 'Timeline'
            },
            categories: categories
        },

        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },

        plotOptions: {
            series: {
                label: {
                    connectorAllowed: false
                },
            }
        },

        series: Object.entries(data).map(itr => {
            return {
                name: itr[0],
                data: itr[1]
            }
        })
    }
}

const Analytics = (props) => {

    const classes = useStyles(style);

    const dispatch = useDispatch();
    const priceTrendForBrands = useSelector((state) => state.analytics.price_trend_for_brands);

    const [priceData, setPriceData] = useState(null)
    const formik = useFormik({
        initialValues: {
            item: ""
        },
        onSubmit: (values, actions) => {
            if (values.item) {
                dispatch(fetchPriceTrendForBrands(values.item));
            }
        }
    });

    const items = useSelector((state) => state.item.list).map(item => {
        return {
            id: {
                entity_id: item.id,
                entity_type: "item"
            },
            name: item.itemName
        }
    });

    useEffect(() => {
        dispatch(getItems())
    }, [dispatch])

    useEffect(() => {

        if (!priceTrendForBrands || !priceTrendForBrands.trend) {
            return;
        }

        const brandMap = priceTrendForBrands.brands.reduce((x, y) => {
            x[y.id] = y.name;
            return x
        }, {})

        const brandTrend = priceTrendForBrands.brands.reduce((x, y) => {
            x[y.name] = [];
            return x
        }, {})

        const trend = priceTrendForBrands.trend.reduce((x, y) => {

            const date = new Date(y._id.date).toDateString()
            const brand = brandMap[y._id.brand];

            if (!x[date]) {
                x[date] = {}
            }

            if (!x[date][brand]) {
                x[date][brand] = {}
            }

            x[date][brand] = y.price;
            return x;

        }, {})

        const categories = []

        const brands = Object.entries(brandMap).map(x => x[1])

        Object.entries(trend).forEach(itr => {
            categories.push(itr[0]);
            brands.forEach(brand => {
                brandTrend[brand].push(itr[1][brand] ? itr[1][brand] : null)
            })
        })

        if (!categories || categories.length === 0) {
            setPriceData(null)
        } else {
            setPriceData(generateOptions("Product Price trend for different Brands", categories, brandTrend))
        }



    }, [priceTrendForBrands])



    return (<Box>
        <Box>
            <form onSubmit={formik.handleSubmit}>
                <Grid container justifyContent="center" spacing={2}>
                    <Grid item xs={3}>
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
                                value={item.id.entity_id}>{item.name}</MenuItem>)}
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

        {priceData && <HighchartsReact highcharts={Highcharts} options={priceData} />}
        {!priceData &&
            <Box sx={{ 
                "display": "flex",
                "flexDirection": "column",
                "justifyContent": "center",
                "alignItems": "center",
                "height": "500px"
            }}>
                <TextSnippetIcon sx={{ color: "secondary.main", "fontSize": "200px" }} />
                <Typography variant='h5' sx={{fontWeight: "bold"}}>No Data Found</Typography>
            </Box>}
    </Box>)
}

export default Analytics;