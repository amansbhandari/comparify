import { failedGetPriceTrend, failedGetPriceTrendForBrands, gotPriceTrend, gotPriceTrendForBrands } from "../reducers/analytics";
import httpClient from "./interceptor";


export const fetchPriceTrend = (itemId) => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/analytics", { params: { "item_id": itemId } });
    dispatch(gotPriceTrend(data));
  } catch (error) {
    dispatch(failedGetPriceTrend(error));
  }
};

export const fetchPriceTrendForBrands = (itemId) => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/analytics/brands", { params: { "item_id": itemId } });
    dispatch(gotPriceTrendForBrands(data));
  } catch (error) {
    dispatch(failedGetPriceTrendForBrands(error));
  }
};