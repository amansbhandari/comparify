import httpClient from "./interceptor";


export const saveStore = (details) => async (dispatch) => {
  try {
    await httpClient.post("/store/", details);
  } catch (error) {
  }
};
