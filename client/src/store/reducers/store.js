// ACTIONS
const STORE_ADD_SUCCESS = "STORE_ADD_SUCCESS";
const STORE_ADD_FAILED = "STORE_ADD_FAILED";


// ACTION CREATORS
export const storeAddSuccess = (success) => {
  return {
    type: STORE_ADD_SUCCESS,
    success
  };
};

export const storeAddFailed = (error) => {
  return {
    type: STORE_ADD_FAILED,
    error
  };
};


// REDUCER
const reducer = (state = {status: ""}, action) => {
  switch (action.type) {
    case STORE_ADD_SUCCESS:
      return {
        status: action.success
      }
    case STORE_ADD_FAILED:
      return {
        status: action.error
      }
    default:
      return state;
  }
};

export default reducer;
