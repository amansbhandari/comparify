// ACTIONS
const GET_USER_ROLE = "GET_USER_ROLE";
const FAILED_GET_USER_ROLE = "FAILED_GET_USER_ROLE"

const GET_ALL_USERS = "GET_ALL_USERS";
const FAILED_GET_USERS = "FAILED_GET_USERS"

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

export const gotAllUsers = (list) => {
  return {
    type: GET_ALL_USERS,
    list
  };
};

export const failedGetUsers = (error) => {
  return {
    type: FAILED_GET_USERS,
    error
  };
}

// REDUCER
const reducer = (state = { role: {}, list: [] }, action) => {
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

    case GET_ALL_USERS:
      return {
        ...state,
        list: action.list
      }

    case FAILED_GET_USERS:
      return {
        ...state,
        list: []
      };

    default:
      return state;
  }
};

export default reducer;
