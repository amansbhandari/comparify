import { gotCountStats, countStatsFailed } from "../reducers/statistics";
import httpClient from "./interceptor";

export const getStatistics = () => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/statistics/");
    dispatch(gotCountStats(data));
  } catch (error) {
    dispatch(countStatsFailed(error));
  }
};
