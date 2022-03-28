import { alertCreated, alertCreationFailed, alertsFetched, alertsFetchedFailed } from "../reducers/alert";
import httpClient from "./interceptor";

export const createAlert = (payload) => async (dispatch) => {
    try {
      const { data } = await httpClient.post("/alert", payload);
      dispatch(alertCreated(data));
      dispatch(fetchAlerts());
    } catch (error) {
      dispatch(alertCreationFailed(error));
    }
};

export const fetchAlerts = () => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/alert");
    dispatch(alertsFetched(data));
  } catch (error) {
    dispatch(alertsFetchedFailed(error));
  }
};