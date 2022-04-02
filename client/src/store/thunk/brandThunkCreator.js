import httpClient from "./interceptor";
import { gotBrands, failedGetBrands } from "../reducers/brand";


export const getBrands = () => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/brand/");
    dispatch(gotBrands(data));
  } catch (error) {
    dispatch(failedGetBrands(error));
  }
};
