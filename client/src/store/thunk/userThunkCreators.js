import axios from "axios";

export const login = (credentials) => async (dispatch) => {
    try {
      const { data } = await axios.post("/user/authenication", credentials);
      localStorage.setItem("messenger-token", data.token);
    } catch (error) {
      console.error(error);
    }
  };