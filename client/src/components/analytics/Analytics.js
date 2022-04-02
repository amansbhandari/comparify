import React, { useEffect, useState } from 'react';
import Highcharts from 'highcharts';
import HighchartsReact from 'highcharts-react-official';
import { useDispatch, useSelector } from 'react-redux';
import { fetchPriceTrendForBrands } from '../../store/thunk/analyticsThunkCreators';


function generateOptions(title, categories, data) {

    if(categories.length === 0 || data.length === 0){
        return null;
    }

    return {
        chart: { type: 'spline' },
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

    const dispatch = useDispatch();
    const priceTrendForBrands = useSelector((state) => state.analytics.price_trend_for_brands);

    const [priceData, setPriceData ] = useState(null)

    useEffect(() => {
        dispatch(fetchPriceTrendForBrands("588fa189-7ed1-41c0-8cc7-eac61304e7b9"));
    }, [dispatch])

    useEffect(() => {

        if(!priceTrendForBrands || !priceTrendForBrands.trend){
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

            if(!x[date]){
                x[date] = {}
            }

            if(!x[date][brand]){
                x[date][brand] = {}
            }

            x[date][brand] = y.price;
            return x;
        
        },{})
        
        const categories = []

        Object.entries(trend).forEach(itr => {
            categories.push(itr[0]);
            Object.entries(itr[1]).forEach(brand => {
                brandTrend[brand[0]].push(brand[1])
            })
        })

        console.log(priceTrendForBrands);
        setPriceData(generateOptions("Price trend for different brands", categories, brandTrend))

    }, [priceTrendForBrands])

    return (<div>
        {priceData && <HighchartsReact highcharts={Highcharts} options={priceData} />}
    </div>)
}

export default Analytics;