// ACTIONS
const GOT_AUTH = "GOT_AUTH";
const FAILED_AUTH = "FAILED_AUTH";

// ACTION CREATORS
export const gotAuth = (auth) => {
  return {
    type: GOT_AUTH,
    auth
  };
};

export const failedAuth = (error) => {
  return {
    type: FAILED_AUTH,
    error
  };
};

// REDUCER
const reducer = (state = {
  token: localStorage.getItem("auth-token")
}, action) => {
  switch (action.type) {
    case GOT_AUTH:
      return action.auth;
    case FAILED_AUTH:
      return action.error
    default:
      return state;
  }
};

export default reducer;
