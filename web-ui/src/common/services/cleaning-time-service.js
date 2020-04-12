import axios from '../utils/api'
import {getAccessToken} from "../utils/token";

export default class CleaningTimeService {

    loginUser(credentials) {
        return axios.post('auth', credentials);
    }

    refreshToken(token) {
        const body = {
            accessToken: token
        };
        const headers = {
            Authorization: getAccessToken()
        };
        return axios.post('auth/refresh', body, {headers});
    }

    registerUser(userDetails) {
        return axios.post('registrations', userDetails);
    }

    getCommunities() {
        return axios.get('communities');
    }

}