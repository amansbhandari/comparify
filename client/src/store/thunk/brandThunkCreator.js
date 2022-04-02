import httpClient from "./interceptor";
import { gotBrands, failedGetBrands } from "../reducers/brand";
import { brandAddSuccess, brandAddFailed } from "../reducers/saveBrand";

export const getBrands = () => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/brand/");
    dispatch(gotBrands(data));
  } catch (error) {
    dispatch(failedGetBrands(error));
  }
};

export const saveBrand = (details) => async (dispatch) => {
  try {
    const { data } = await httpClient.post("/brand/", details);
    dispatch(brandAddSuccess(data));
  } catch (error) {
    dispatch(brandAddFailed(error));
  }
};
