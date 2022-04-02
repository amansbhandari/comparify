// ACTIONS
const GOT_PRICE_TREND = "GOT_PRICE_TREND";
const FAILED_GET_PRICE_TREND = "FAILED_GET_PRICE_TREND"

const GOT_PRICE_TREND_FOR_BRANDS = "GOT_PRICE_TREND_FOR_BRANDS";
const FAILED_GET_PRICE_TREND_FOR_BRANDS = "FAILED_GET_PRICE_TREND_FOR_BRANDS"

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

// REDUCER
const reducer = (state = { price_trend: [], price_trend_for_brands: {} }, action) => {
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

    default:
      return state;
  }
};

export default reducer;
