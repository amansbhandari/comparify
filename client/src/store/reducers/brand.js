// ACTIONS
const GOT_BRAND = "GOT_BRAND";
const FAILED_GET_BRAND = "FAILED_GET_BRAND"

// ACTION CREATORS
export const gotBrands = (brands) => {
  return {
    type: GOT_BRAND,
    brands
  };
};

export const failedGetBrands = (error) => {
  return {
    type: FAILED_GET_BRAND,
    error
  };
}

// REDUCER
const reducer = (state = { list: [] }, action) => {
  switch (action.type) {
    case GOT_BRAND:
      return {
        ...state,
        list: action.brands
      }

    case FAILED_GET_BRAND:
      return {
        ...state,
        list: []
      };

    default:
      return state;
  }
};

export default reducer;
