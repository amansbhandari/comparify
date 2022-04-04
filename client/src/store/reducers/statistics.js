// ACTIONS
const GOT_COUNT = "GOT_COUNT";
const COUNT_FAILED = "COUNT_FAILED";

// ACTION CREATORS
export const gotCountStats = (response) => {
  return {
    type: GOT_COUNT,
    response,
  };
};

export const countStatsFailed = (error) => {
  return {
    type: COUNT_FAILED,
    error,
  };
};

// REDUCER
const reducer = (state = { stats: "" }, action) => {
  switch (action.type) {
    case GOT_COUNT:
      return {
        stats: action.response,
      };
    case COUNT_FAILED:
      return {
        stats: null,
      };
    default:
      return state;
  }
};

export default reducer;
