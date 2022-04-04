// ACTIONS
const GOT_ITEM = "GOT_ITEM";
const FAILED_GET_ITEM = "FAILED_GET_ITEM";

// ACTION CREATORS
export const gotItem = (item) => {
  return {
    type: GOT_ITEM,
    item,
  };
};

export const failedGetItem = (error) => {
  return {
    type: FAILED_GET_ITEM,
    error,
  };
};

// REDUCER
const reducer = (state = { item: null }, action) => {
  switch (action.type) {
    case GOT_ITEM:
      return {
        ...state,
        item: action.item ,
      };

    case FAILED_GET_ITEM:
      return {
        ...state,
        item: null,
      };

    default:
      return state;
  }
};

export default reducer;
