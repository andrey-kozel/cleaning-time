import {REFRESH_FAILED, REFRESH_REQUEST, REFRESH_SUCCESS} from "../action-types";
import {getAccessToken, isExpiredInASeconds} from "../utils/token";

const refreshToken = () => {
    return {
        type: REFRESH_REQUEST,
    }
};

const refreshSuccess = (token) => {
    return {
        type: REFRESH_SUCCESS,
        payload: token
    }
};

const refreshFailed = (response) => {
    return {
        type: REFRESH_FAILED,
        payload: response
    }
};

const performRefresh = (cleaningTimeService, dispatch) => () => {
    dispatch(refreshToken());
    const accessToken = getAccessToken();
    cleaningTimeService.refreshToken(accessToken)
        .then(response => dispatch(refreshSuccess(response.data)))
        .catch(error => dispatch(refreshFailed(error.response)));
};

const isTokenAlmostExpired = () => {
    return isExpiredInASeconds(60);
};

export {
    performRefresh,
    isTokenAlmostExpired
};