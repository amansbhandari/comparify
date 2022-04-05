import { failedGetMonthlyTotalPurchaseOfItemCategory, failedGetPriceTrend, failedGetPriceTrendForBrands, failedGetProductCountForCategory, gotMonthlyTotalPurchaseOfItemCategory, gotPriceTrend, gotPriceTrendForBrands, gotProductCountForCategory } from "../reducers/analytics";
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

export const fetchProductCountForCategory = (date) => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/analytics/categories", { params: { "date": date } });
    dispatch(gotProductCountForCategory(data));
  } catch (error) {
    dispatch(failedGetProductCountForCategory(error));
  }
};

export const fetchMonthlyTotalPurchaseOfItemCategory = (month) => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/analytics/monthly", { params: { month} });
    dispatch(gotMonthlyTotalPurchaseOfItemCategory(data));
  } catch (error) {
    dispatch(failedGetMonthlyTotalPurchaseOfItemCategory(error));
  }
};