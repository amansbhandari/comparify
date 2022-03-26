import { alertCreated, alertCreationFailed } from "../reducers/alert";
import httpClient from "./interceptor";

export const createAlert = (payload) => async (dispatch) => {
    try {
      const { data } = await httpClient.post("/alert/", payload);
      dispatch(alertCreated(data));
    } catch (error) {
      dispatch(alertCreationFailed(error));
    }
};