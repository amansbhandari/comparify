import httpClient from "./interceptor";
import { receiverRegistered, receiverRegistrationFailed } from "../reducers/notification";

export const registerNotificationReceiver = (credentials) => async (dispatch) => {
  try {
    const { data } = await httpClient.post("/notification/receiver/register", credentials);
    dispatch(receiverRegistered(data));
  } catch (error) {
    dispatch(receiverRegistrationFailed(error));
  }
};