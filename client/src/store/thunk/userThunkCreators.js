import httpClient from "./interceptor";
import { failedAuth, failedLogout, gotAuth, gotLogout } from "../reducers/authentication";
import { failedGetUserRole, failedGetUsers, gotAllUsers, gotUserRole } from "../reducers/user";
import { closeSocket, openSocket } from "../../socket";


export const authenication = (credentials) => async (dispatch) => {
  try {
    const { data } = await httpClient.post("/user/authentication", credentials);
    dispatch(gotAuth(data));
    localStorage.setItem("user-id", credentials.user_identifier)
    localStorage.setItem("auth-token", data.token);
    openSocket();
  } catch (error) {
    dispatch(failedAuth(error));
    localStorage.removeItem("auth-token")
  }
};

export const getUserRole = () => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/user/role");
    dispatch(gotUserRole(data));
  } catch (error) {
    dispatch(failedGetUserRole(error)); 
  }
};

export const logout = () => async (dispatch) => {
  try {
    await httpClient.get("/user/logout");
    dispatch(gotLogout());
    localStorage.removeItem("auth-token")
    closeSocket()
  } catch (error) {
    dispatch(failedLogout(error));
  }
};

export const getDetails = (username) => {
  return httpClient.get("/user/details?username=" + username).then((response) => {
      let data = response.data
      return data;
  }, (error) => {
    alert(error);
  });
};

export const saveDetails = (details) => {
return httpClient.post("/user/details", details).then((response) => {
      return details
  }, (error) => {
    alert(error);
  });
};

export const signup = (credentials) => async (dispatch) => {
  try {
    await httpClient.post("/user/register", credentials);
  } catch (error) {}
};


export const addproducts = (a) => {
  return httpClient.post("/compareitems/", a).then((response) => {
    return a
}, (error) => {
  alert(error);
});
}; 


  export const itemCategories = () => {
  return httpClient.get("/itemcategories/" ).then((response) => {
      let data = response.data
      return data;
  }, (error) => {
    alert(error);
  });
};


export const brandListDropDown = () => {
  return httpClient.get("/brand/" ).then((response) => {
      let data = response.data
      return data;
  }, (error) => {
    alert(error);
  });
};


export const productListDropDown = () => {
  return httpClient.get("/item/" ).then((response) => {
      let data = response.data
      return data;
  }, (error) => {
    alert(error);
  });
};



export const storeListDropDown = () => {
  return httpClient.get("/store/" ).then((response) => {
      let data = response.data
      return data;
  }, (error) => {
    alert(error);
  });
};


export const addfeedback = (fb) => {
  return httpClient.post("/feedback", fb).then((response) => {
    return response
}, (error) => {
  alert(error);
});
}; 

export const addAdminproducts = (item) => {
  return httpClient.post("/item/", item).then((response) => {
    return item
}, (error) => {
  alert(error);
});
};


export const getAllUser = () => async (dispatch) => {
  try {
    const { data } = await httpClient.get("/user/all");
    dispatch(gotAllUsers(data));
  } catch (error) {
    dispatch(failedGetUsers(error)); 
  }
};