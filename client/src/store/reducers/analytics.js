// ACTIONS
const GOT_PRICE_TREND = "GOT_PRICE_TREND";
const FAILED_GET_PRICE_TREND = "FAILED_GET_PRICE_TREND"

const GOT_PRICE_TREND_FOR_BRANDS = "GOT_PRICE_TREND_FOR_BRANDS";
const FAILED_GET_PRICE_TREND_FOR_BRANDS = "FAILED_GET_PRICE_TREND_FOR_BRANDS"

const GOT_PRODUCT_COUNT_FOR_CATEGORY = "GOT_PRODUCT_COUNT_FOR_CATEGORY"
const FAILED_GET_PRODUCT_COUNT_FOR_CATEGORY = "FAILED_GET_PRODUCT_COUNT_FOR_CATEGORY"

const GOT_MONTHLY_TOTAL_PURCHASE_OF_ITEM_CATEGORY = "GOT_MONTHLY_TOTAL_PURCHASE_OF_ITEM_CATEGORY"
const FAILED_GET_MONTHLY_TOTAL_PURCHASE_OF_ITEM_CATEGORY = "FAILED_GET_MONTHLY_TOTAL_PURCHASE_OF_ITEM_CATEGORY"

// ACTION CREATORS
export const gotPriceTrend = (price_trend) => {
  return {
    type: GOT_PRICE_TREND,
    price_trend
  };
};

export const failedGetPriceTrend = (error) => {
  return {
    type: FAILED_GET_PRICE_TREND,
    error
  };
}

export const gotPriceTrendForBrands = (price_trend) => {
  return {
    type: GOT_PRICE_TREND_FOR_BRANDS,
    price_trend
  };
};

export const failedGetPriceTrendForBrands = (error) => {
  return {
    type: FAILED_GET_PRICE_TREND_FOR_BRANDS,
    error
  };
}

export const gotProductCountForCategory = (data) => {
  return {
    type: GOT_PRODUCT_COUNT_FOR_CATEGORY,
    data
  };
}

export const failedGetProductCountForCategory = (error) => {
  return {
    type: FAILED_GET_PRODUCT_COUNT_FOR_CATEGORY,
    error
  };
}

export const gotMonthlyTotalPurchaseOfItemCategory = (data) => {
  return {
    type: GOT_MONTHLY_TOTAL_PURCHASE_OF_ITEM_CATEGORY,
    data
  };
}

export const failedGetMonthlyTotalPurchaseOfItemCategory = (error) => {
  return {
    type: FAILED_GET_MONTHLY_TOTAL_PURCHASE_OF_ITEM_CATEGORY,
    error
  };
}


// REDUCER
const reducer = (state = {
  price_trend: [],
  price_trend_for_brands: {},
  product_count_for_category: [],
  monthly_total_purchase_of_item_category: {}
}, action) => {

  switch (action.type) {
    case GOT_PRICE_TREND:
      return {
        ...state,
        price_trend: action.price_trend
      }

    case FAILED_GET_PRICE_TREND:
      return {
        ...state,
        price_trend: []
      };

    case GOT_PRICE_TREND_FOR_BRANDS:
      return {
        ...state,
        price_trend_for_brands: action.price_trend
      }

    case FAILED_GET_PRICE_TREND_FOR_BRANDS:
      return {
        ...state,
        price_trend_for_brands: {}
      };

    case GOT_PRODUCT_COUNT_FOR_CATEGORY:
      return {
        ...state,
        product_count_for_category: action.data
      }

    case FAILED_GET_PRODUCT_COUNT_FOR_CATEGORY:
      return {
        ...state,
        product_count_for_category: []
      };

    case GOT_MONTHLY_TOTAL_PURCHASE_OF_ITEM_CATEGORY:
      return {
        ...state,
        monthly_total_purchase_of_item_category: action.data
      }

    case FAILED_GET_MONTHLY_TOTAL_PURCHASE_OF_ITEM_CATEGORY:
      return {
        ...state,
        monthly_total_purchase_of_item_category: {}
      }
    default:
      return state;
  }
};

export default reducer;
