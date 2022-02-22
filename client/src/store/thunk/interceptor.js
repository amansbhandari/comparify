import axios from "axios";

const httpClient = axios.create({ baseURL: process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_SERVER_CONTEXT_PATH })
    
httpClient.interceptors.request.use(async function (config) {
    
    const token = localStorage.getItem("auth-token");
    config.headers["Authorization"] = token;
    
    return config;
});

export default httpClient;
