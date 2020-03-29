import {createReducer} from 'redux-create-reducer';
import {getAccessToken} from "../../utils/token";

const LOGIN_REQUEST = "cleaningTime/login/LOGIN_REQUEST";
const LOGIN_SUCCESS = "cleaningTime/login/LOGIN_SUCCESS";
const LOGIN_FAILED = "cleaningTime/login/LOGIN_FAILED";
const LOGOUT = "cleaningTime/header/logout/LOGOUT";

const loginUser = () => {
    return {
        type: LOGIN_REQUEST,
    }
};

const loginUserSuccess = (token) => {
    return {
        type: LOGIN_SUCCESS,
        payload: token
    }
};

const loginUserFailed = (response) => {
    return {
        type: LOGIN_FAILED,
        payload: response
    }
};

const logout = () => {
    return {
        type: LOGOUT
    }
};

const performLogin = (cleaningTimeService, dispatch) => (credentials, history) => {
    dispatch(loginUser());
    cleaningTimeService.loginUser(credentials)
        .then(response => dispatch(loginUserSuccess(response.data)))
        .then(() => history.push("/home"))
        .catch(error => dispatch(loginUserFailed(error.response)));
};

const performLogout = (dispatch) => (history) => {
    dispatch(logout());
    history.push("/login")
};

const initialState = {
    loginInProgress: false,
    loginSuccessful: null,
    accessToken: getAccessToken()
};

const setLoginRequested = () => ({
    loginInProgress: true,
    loginSuccessful: null,
    accessToken: null
});

const setLoginSucceed = (state, action) => ({
    loginInProgress: false,
    loginSuccessful: true,
    accessToken: action.payload
});

const setLoginFailed = () => ({
    loginInProgress: false,
    loginSuccessful: false,
    accessToken: null
});

const setLogoutRequested = () => ({
    loginInProgress: false,
    loginSuccessful: null,
    accessToken: null
});

const loginReducer = createReducer(initialState, {
    [LOGIN_REQUEST]: setLoginRequested,
    [LOGIN_SUCCESS]: setLoginSucceed,
    [LOGIN_FAILED]: setLoginFailed,
    [LOGOUT]: setLogoutRequested
});

export {
    LOGIN_SUCCESS,
    LOGOUT,
    performLogin,
    performLogout
};

export default loginReducer;