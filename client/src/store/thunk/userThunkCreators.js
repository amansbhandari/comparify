import httpClient from "./interceptor";

export const authenication = (credentials) => async (dispatch) => {
    try {
      const { data } = await httpClient.post("/user/authentication", credentials);
      localStorage.setItem("auth-token", data.token);
    } catch (error) {
      localStorage.removeItem("auth-token")
    }
};