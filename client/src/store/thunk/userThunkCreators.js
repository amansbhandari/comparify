import httpClient from "./interceptor";
import { failedAuth, failedLogout, gotAuth, gotLogout } from "../reducers/authentication";
import { failedGetUserRole, gotUserRole } from "../reducers/user";

export const authenication = (credentials) => async (dispatch) => {

  try {
    const { data } = await httpClient.post("/user/authentication", credentials);
    dispatch(gotAuth(data));
    localStorage.setItem("user-id", credentials.user_identifier)
    localStorage.setItem("auth-token", data.token);
  } catch (error) {
    dispatch(failedAuth(error));
    localStorage.removeItem("auth-token")
  }
};

export const getUserRole = () => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/user/role");
    dispatch(gotUserRole(data));
  } catch (error) {
    dispatch(failedGetUserRole(error));
  }
};

export const logout = () => async (dispatch) => {
  try {
    await httpClient.get("/user/logout");
    dispatch(gotLogout());
    localStorage.removeItem("auth-token")
  } catch (error) {
    dispatch(failedLogout(error));
  }
};

export const getDetails = (username) => {
  return httpClient.get("/user/details?username=" + username).then((response) => {
      let data = response.data
      return data;
  }, (error) => {
    alert(error);
  });
};

export const saveDetails = (details) => {
return httpClient.post("/user/details", details).then((response) => {
      return details
  }, (error) => {
    alert(error);
  });
};