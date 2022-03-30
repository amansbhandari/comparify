// ACTIONS
const ALERT_CREATED = "ALERT_CREATED";
const ALERTS_FETCHTED = "ALERTS_FETCHTED";
const NEW_ALERT = "NEW_ALERT";

const ALERT_CREATION_FAILED = "ALERT_CREATION_FAILED";
const ALERTS_FETCHTED_FAILED = "ALERTS_FETCHTED_FAILED";

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

export const alertsFetched = (alerts) => {
  return {
    type: ALERTS_FETCHTED,
    alerts
  };
};

export const alertsFetchedFailed = (alerts) => {
  return {
    type: ALERTS_FETCHTED_FAILED,
    alerts
  };
};

export const newAlert = (alert) => {
  return {
    NEW_ALERT,
    alert
  };
};

const initState = { recent_alert_created: {}, alerts: []}

// REDUCER
const reducer = (state = initState, action) => {
  switch (action.type) {
    case ALERT_CREATED:
      return {...state, recent_alert_created: action.alert} ;
    case ALERT_CREATION_FAILED:
      return {...state, recent_alert_created: action.alert} 
    case ALERTS_FETCHTED:
      return {...state, alerts: action.alerts} 
    case ALERTS_FETCHTED_FAILED:
      return {...state, alerts: []}
    case NEW_ALERT:
      return {...state }
    default:
      return state;
  }
};

export default reducer;
