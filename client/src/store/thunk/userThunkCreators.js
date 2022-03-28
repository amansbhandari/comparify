import httpClient from "./interceptor";
import { failedAuth, gotAuth } from "../reducers/authentication";

export const authenication = (credentials) => async (dispatch) => {
    try {
      const { data } = await httpClient.post("/user/authentication", credentials);
      dispatch(gotAuth(data));
      localStorage.setItem("auth-token", data.token);
      localStorage.setItem("user_identifier", credentials.user_identifier)
    } catch (error) {
      dispatch(failedAuth(error));
      localStorage.removeItem("auth-token")
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