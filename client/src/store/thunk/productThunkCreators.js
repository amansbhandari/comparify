import { alertsFetchedFailed } from "../reducers/alert";
import httpClient from "./interceptor";
import { gotProductList } from "../reducers/product";

export const searchProduct = (name) => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/product/search?name="+ name);
    dispatch(gotProductList(data));
  } catch (error) {
    dispatch(alertsFetchedFailed(error));
  }
};
