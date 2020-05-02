import {createSlice} from "@reduxjs/toolkit";
import {getAccessToken, isExpiredInASeconds} from "../../../common/utils/token";
import cleaningTimeService from '../../../common/services/cleaning-time-service';

const refreshSlice = createSlice({
    name: 'refresh',
    initialState: {
        refreshInProgress: false,
        refreshSuccessful: null
    },
    reducers: {
        refreshTokenRequested: (state) => {
            state.refreshInProgress = true;
            state.refreshSuccessful = null;
        },
        refreshTokenSucceed: (state) => {
            state.refreshInProgress = false;
            state.refreshSuccessful = true;
        },
        refreshTokenFailed: (state) => {
            state.refreshInProgress = false;
            state.refreshSuccessful = false;
        }
    }
})

const performRefresh = () => dispatch => {
    if (!isExpiredInASeconds(60)) {
        return
    }
    dispatch(refreshSlice.actions.refreshTokenRequested());
    const accessToken = getAccessToken();
    cleaningTimeService.refreshToken(accessToken)
        .then(response => dispatch(refreshSlice.actions.refreshTokenSucceed(response.data)))
        .catch(() => dispatch(refreshSlice.actions.refreshTokenFailed()));
};

export {
    performRefresh
};

export const REFRESH_SUCCESS = refreshSlice.actions.refreshTokenSucceed.type;

export default refreshSlice.reducer;