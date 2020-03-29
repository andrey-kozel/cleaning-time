import {createReducer} from 'redux-create-reducer';
import {getAccessToken, isExpiredInASeconds} from "../../../common/utils/token";

const REFRESH_REQUEST = "cleaningTime/token-refresh/REFRESH_REQUEST";
const REFRESH_SUCCESS = "cleaningTime/token-refresh/REFRESH_SUCCESS";
const REFRESH_FAILED = "cleaningTime/token-refresh/REFRESH_FAILED";

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

const initialState = {
    refreshInProgress: false,
    refreshSuccessful: null
};

const setRefreshRequested = () => ({
    refreshInProgress: true,
    refreshSuccessful: null
});

const setRefreshSucceed = () => ({
    refreshInProgress: false,
    refreshSuccessful: true
});

const setRefreshFailed = () => ({
    refreshInProgress: false,
    refreshSuccessful: false
});

const refreshReducer = createReducer(initialState, {
    [REFRESH_REQUEST]: setRefreshRequested,
    [REFRESH_SUCCESS]: setRefreshSucceed,
    [REFRESH_FAILED]: setRefreshFailed
});

export {
    REFRESH_SUCCESS,
    performRefresh,
    isTokenAlmostExpired
};

export default refreshReducer;