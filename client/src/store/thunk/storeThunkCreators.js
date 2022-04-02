import { storeAddFailed, storeAddSuccess } from "../reducers/store";
import httpClient from "./interceptor";

export const saveStore = (details) => async (dispatch) => {
  try {
    const { data } = await httpClient.post("/store/", details);
    dispatch(storeAddSuccess(data));
  } catch (error) {
    dispatch(storeAddFailed(error));
  }
};
