import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8080/api/v1",
    headers: {
        "Accept": "*/*",
        "Content-Type": "application/json"
    },
    responseType: "json"
});