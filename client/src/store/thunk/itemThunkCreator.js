import httpClient from "./interceptor";
import { gotItems, failedGetItems } from "../reducers/item";


export const getItems  = () => async (dispatch) => {
    try {
        const { data } = await httpClient.get("/item/");
        dispatch(gotItems(data));
      } catch (error) {
        dispatch(failedGetItems(error)); 
      }
  };
  