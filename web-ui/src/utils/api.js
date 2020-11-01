import axios from "axios";
import {getAccessToken} from "./token";

const customAxios = axios.create({
    baseURL: "http://localhost:8080/api/v1",
    headers: {
        "Accept": "*/*",
        "Content-Type": "application/json"
    },
    responseType: "json"
});

customAxios.interceptors.request.use((config) => {
    const token = getAccessToken();
    if (token) {
        config.headers.Authorization = token;
    }
    return config;
});

export default customAxios;