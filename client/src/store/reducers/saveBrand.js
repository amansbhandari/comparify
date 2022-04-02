// ACTIONS
const BRAND_ADD_SUCCESS = "STORE_ADD_SUCCESS";
const BRAND_ADD_FAILED = "STORE_ADD_FAILED";

// ACTION CREATORS
export const brandAddSuccess = (success) => {
  return {
    type: BRAND_ADD_SUCCESS,
    success,
  };
};

export const brandAddFailed = (error) => {
  return {
    type: BRAND_ADD_FAILED,
    error,
  };
};

// REDUCER
const reducer = (state = { response: "" }, action) => {
  switch (action.type) {
    case BRAND_ADD_SUCCESS:
      return {
        response: action.success,
      };
    case BRAND_ADD_FAILED:
      return {
        response: action.error,
      };
    default:
      return state;
  }
};

export default reducer;
