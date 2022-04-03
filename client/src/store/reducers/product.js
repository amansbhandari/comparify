// ACTIONS
const GET_PRODUCT_LIST = "GET_PRODUCT_LIST";


// ACTION CREATORS
export const gotProductList = (list) => {
  return {
    type: GET_PRODUCT_LIST,
    list,
  };
};

// REDUCER
const reducer = (state = { search: [] }, action) => {
  switch (action.type) {
    case GET_PRODUCT_LIST:
      return {
        ...state,
        search: action.list,
      };

    default:
      return state;
  }
};

export default reducer;
