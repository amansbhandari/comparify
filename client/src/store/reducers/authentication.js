// ACTIONS
const GOT_AUTH = "GOT_AUTH";
const FAILED_AUTH = "FAILED_AUTH";

const LOGOUT = "LOGOUT"
const FAILED_LOGOUT = "FAILED_LOGOUT"

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

export const gotLogout = (status) => {
  return {
    type: LOGOUT,
    status
  };
};

export const failedLogout = (error) => {
  return {
    type: FAILED_LOGOUT,
    error
  };
}

// REDUCER
const reducer = (state = {
  token: localStorage.getItem("auth-token"),
  logout: {}
}, action) => {
  switch (action.type) {
    case GOT_AUTH:
      return action.auth;
    case FAILED_AUTH:
      return action.error
      case LOGOUT:
        return {logout: action.logout};
  
      case FAILED_LOGOUT:
        return { ...state, logout: action.error };
    default:
      return state;
  }
};

export default reducer;
