import axios from "axios";

const httpClient = axios.create({ baseURL: process.env.REACT_APP_SERVER_BASE_URL })
    
httpClient.interceptors.request.use(async function (config) {
    config.url = process.env.REACT_APP_SERVER_BASE_URL + config.url
    
    const token = localStorage.getItem("auth-token");
    config.headers["Authorization"] = token;
    
    return config;
});

export default httpClient;
