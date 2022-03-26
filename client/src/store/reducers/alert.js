// ACTIONS
const ALERT_CREATED = "ALERT_CREATED";
const ALERT_CREATION_FAILED = "ALERT_CREATION_FAILEDw";

// ACTION CREATORS
export const alertCreated = (alert) => {
  return {
    type: ALERT_CREATED,
    alert
  };
};

export const alertCreationFailed = (error) => {
  return {
    type: ALERT_CREATION_FAILED,
    error
  };
};

// REDUCER
const reducer = (state = {}, action) => {
  switch (action.type) {
    case ALERT_CREATED:
      return action.alert;
    case ALERT_CREATION_FAILED:
      return action.error
    default:
      return state;
  }
};

export default reducer;
