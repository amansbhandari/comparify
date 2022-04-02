import httpClient from "./interceptor";
import { failedFetchingNotification, fetchedNotification, receiverRegistered, receiverRegistrationFailed } from "../reducers/notification";

export const registerNotificationReceiver = (credentials) => async (dispatch) => {
  try {
    const { data } = await httpClient.post("/notification/receiver/register", credentials);
    dispatch(receiverRegistered(data));
  } catch (error) {
    dispatch(receiverRegistrationFailed(error));
  }
};

export const fetchNotifications = () => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/notification");
    dispatch(fetchedNotification(data));
  } catch (error) {
    dispatch(failedFetchingNotification(error));
  }
};