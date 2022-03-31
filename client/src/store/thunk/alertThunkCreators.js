import { toast } from "react-toastify";
import { alertCreated, alertCreationFailed, alertDeleted, 
  alertDeletionFailed, alertsFetched, alertsFetchedFailed } from "../reducers/alert";
import httpClient from "./interceptor";

export const createAlert = (payload) => async (dispatch) => {
    try {
      const { data } = await httpClient.post("/alert", payload);
      dispatch(alertCreated(data));
      dispatch(fetchAlerts());
      toast.success(payload.alert_identifier+" Alert Created!")
    } catch (error) {
      dispatch(alertCreationFailed(error));
      toast.error(payload.alert_identifier+" Alert Failed!")
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

export const deleteAlert = (alertIdentifier, payload) => async (dispatch) => {
  try {
    const { data } = await httpClient.post("/alert/delete", null, { params: payload });
    dispatch(alertDeleted(data));
    dispatch(fetchAlerts());
    toast.success(alertIdentifier+" Alert Deleted!")
  } catch (error) {
    dispatch(alertDeletionFailed(error));
    toast.error(alertIdentifier+" Alert Deletion Failed!")
  }
};