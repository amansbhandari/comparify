// ACTIONS
const GET_USER_ROLE = "GET_USER_ROLE";
const FAILED_GET_USER_ROLE = "FAILED_GET_USER_ROLE"

// ACTION CREATORS
export const gotUserRole = (role) => {
  return {
    type: GET_USER_ROLE,
    role
  };
};

export const failedGetUserRole = (error) => {
  return {
    type: FAILED_GET_USER_ROLE,
    error
  };
}

// REDUCER
const reducer = (state = { role: {}, logout: {} }, action) => {
  switch (action.type) {
    case GET_USER_ROLE:
      return {
        ...state,
        role: action.role
      }

    case FAILED_GET_USER_ROLE:
      return {
        ...state,
        role: action.error
      };

    default:
      return state;
  }
};

export default reducer;
