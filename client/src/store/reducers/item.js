// ACTIONS
const GOT_ITEM = "GOT_ITEM";
const FAILED_GET_ITEM = "FAILED_GET_ITEM"

// ACTION CREATORS
export const gotItems = (items) => {
  return {
    type: GOT_ITEM,
    items
  };
};

export const failedGetItems = (error) => {
  return {
    type: FAILED_GET_ITEM,
    error
  };
}

// REDUCER
const reducer = (state = { list: [] }, action) => {
  switch (action.type) {
    case GOT_ITEM:
      return {
        ...state,
        list: action.items
      }

    case FAILED_GET_ITEM:
      return {
        ...state,
        list: []
      };

    default:
      return state;
  }
};

export default reducer;
