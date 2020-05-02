import axios from '../utils/api'
import {getAccessToken} from "../utils/token";

class CleaningTimeService {

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

    getCommunity(communityId) {
        return axios.get(`communities/${communityId}`);
    }

    saveCommunity(community) {
        return axios.post('communities', community);
    }

    updateCommunity(id, community) {
        return axios.put(`communities/${id}`, community);
    }

    deleteCommunity(id) {
        return axios.delete(`communities/${id}`);
    }

}

export default new CleaningTimeService();