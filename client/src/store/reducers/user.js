// ACTIONS
const GET_USER = "GET_USER";

// ACTION CREATORS
export const gotUser = (user) => {
  return {
    type: GET_USER,
    user
  };
};

// REDUCER
const reducer = (state = { isFetching: true }, action) => {
  switch (action.type) {
    case GET_USER:
      return action.user;
    default:
      return state;
  }
};

export default reducer;
