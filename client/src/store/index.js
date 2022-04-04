import { createStore, applyMiddleware, combineReducers } from "redux";
import loggerMiddleware from "redux-logger";
import thunkMiddleware from "redux-thunk";

import authentication from "./reducers/authentication";
import user from "./reducers/user";
import alert from "./reducers/alert";
import product from "./reducers/product";
import item from "./reducers/item";
import brand from "./reducers/brand";
import notification from "./reducers/notification";
import store from "./reducers/store";
import saveBrand from "./reducers/saveBrand";
import analytics from "./reducers/analytics";
import search from "./reducers/search";
import statistics from "./reducers/statistics";

const CLEAR_ON_LOGOUT = "CLEAR_ON_LOGOUT";

export const clearOnLogout = () => {
  return {
    type: CLEAR_ON_LOGOUT,
  };
};

const appReducer = combineReducers({
  authentication,
  user,
  alert,
  product,
  item,
  brand,
  notification,
  store,
  saveBrand,
  analytics,
  search,
  statistics
});

const rootReducer = (state, action) => {
  if (action.type === CLEAR_ON_LOGOUT) {
    state = undefined;
  }
  return appReducer(state, action);
};

export default createStore(
  rootReducer,
  applyMiddleware(thunkMiddleware, loggerMiddleware)
);
