import React from 'react';
import { Box } from '@mui/system';
import PriceTrendForDifferentBrands from './PriceTrendForDifferentBrands';
import { Divider } from '@material-ui/core';
import ProductCountForCategory from './ProductCountForCategory';
import MonthlyTotalPurchaseOfItemCategory from './MonthlyTotalPurchaseOfItemCategory';

const Analytics = (props) => {

    return (<Box>
        <PriceTrendForDifferentBrands />
        <Divider />
        <ProductCountForCategory />
        <Divider />
        <MonthlyTotalPurchaseOfItemCategory />
    </Box>)
}

export default Analytics;