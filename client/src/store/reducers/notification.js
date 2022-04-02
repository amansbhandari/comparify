// ACTIONS
const RECEIVER_REGISTERED = "RECEIVER_REGISTERED";
const RECEIVER_REGISTRATION_FAILED = "RECEIVER_REGISTRATION_FAILED"

const FETCH_NOTIFICATION = "FETCH_NOTIFICATION";
const FAILED_FETCHING_NOTIFICATION = "FAILED_FETCHING_NOTIFICATION"

// ACTION CREATORS
export const receiverRegistered = (status) => {
  return {
    type: RECEIVER_REGISTERED,
    status
  };
};

export const receiverRegistrationFailed = (error) => {
  return {
    type: RECEIVER_REGISTRATION_FAILED,
    error
  };
}

export const fetchedNotification = (list) => {
  return {
    type: FETCH_NOTIFICATION,
    list
  };
};

export const failedFetchingNotification = (error) => {
  return {
    type: FAILED_FETCHING_NOTIFICATION,
    error
  };
}

// REDUCER
const reducer = (state = { registration: false, list: [] }, action) => {
  switch (action.type) {

    case RECEIVER_REGISTERED:
      return { ...state, registration: true }

    case RECEIVER_REGISTRATION_FAILED:
      return { ...state, registration: false }

    case FETCH_NOTIFICATION:
      return { ...state, list: action.list }

    case FAILED_FETCHING_NOTIFICATION:
      return { ...state, list: [] }

    default:
      return state;
  }
};

export default reducer;
