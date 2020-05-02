import {createSlice} from "@reduxjs/toolkit";

import {getAccessToken} from "../../utils/token";
import cleaningTimeService from "../../services/cleaning-time-service";

const authSlice = createSlice({
    name: 'auth',
    initialState: {
        loginInProgress: false,
        loginSuccessful: null,
        accessToken: getAccessToken()
    },
    reducers: {
        loginRequested: (state) => {
            state.loginInProgress = true;
            state.loginSuccessful = null;
            state.accessToken = null;
        },
        loginSucceed: (state, action) => {
            state.loginInProgress = false;
            state.loginSuccessful = true;
            state.accessToken = action.payload;
        },
        loginFailed: (state) => {
            state.loginInProgress = false;
            state.loginSuccessful = false;
            state.accessToken = null;
        },
        logout: (state) => {
            state.loginInProgress = false;
            state.loginSuccessful = null;
            state.accessToken = null;
        }
    }
})

const performLogin = (credentials, history) => dispatch => {
    dispatch(authSlice.actions.loginRequested());
    cleaningTimeService.loginUser(credentials)
        .then(response => dispatch(authSlice.actions.loginSucceed(response.data)))
        .then(() => history.push("/home"))
        .catch(error => dispatch(authSlice.actions.loginFailed(error.response)));
};

const performLogout = (history) => dispatch => {
    dispatch(authSlice.actions.logout());
    history.push("/login")
};

export {
    performLogin,
    performLogout
};

export const LOGIN_SUCCESS = authSlice.actions.loginSucceed.type;
export const LOGOUT = authSlice.actions.logout.type;

export default authSlice.reducer;