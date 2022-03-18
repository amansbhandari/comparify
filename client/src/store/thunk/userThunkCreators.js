import httpClient from "./interceptor";
import { failedAuth, gotAuth } from "../reducers/authentication";

export const authenication = (credentials) => async (dispatch) => {
    try {
      const { data } = await httpClient.post("/user/authentication", credentials);
      dispatch(gotAuth(data));
      localStorage.setItem("auth-token", data.token);
    } catch (error) {
      dispatch(failedAuth(error));
      localStorage.removeItem("auth-token")
    }
};