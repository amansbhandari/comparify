// ACTIONS
const RECEIVER_REGISTERED = "RECEIVER_REGISTERED";
const RECEIVER_REGISTRATION_FAILED = "RECEIVER_REGISTRATION_FAILED"

// ACTION CREATORS
export const receiverRegistered = (role) => {
  return {
    type: RECEIVER_REGISTERED,
    role
  };
};

export const receiverRegistrationFailed = (error) => {
  return {
    type: RECEIVER_REGISTRATION_FAILED,
    error
  };
}

// REDUCER
const reducer = (state = {registration: false}, action) => {
  switch (action.type) {
    case RECEIVER_REGISTERED:
      return { registration: true }

    case RECEIVER_REGISTRATION_FAILED:
        return { registration: true }
    default:
      return state;
  }
};

export default reducer;
