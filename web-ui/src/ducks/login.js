import {createReducer} from 'redux-create-reducer';
import {getAccessToken} from "../utils/token";

const LOGIN_REQUEST = "cleaningTime/login/LOGIN_REQUEST";
const LOGIN_SUCCESS = "cleaningTime/login/LOGIN_SUCCESS";
const LOGIN_FAILED = "cleaningTime/login/LOGIN_FAILED";

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

const performLogin = (cleaningTimeService, dispatch) => (credentials, history) => {
    dispatch(loginUser());
    cleaningTimeService.loginUser(credentials)
        .then(response => dispatch(loginUserSuccess(response.data)))
        .then(() => history.push("/home"))
        .catch(error => dispatch(loginUserFailed(error.response)));
};

const initialState = {
    loginInProgress: false,
    accessToken: getAccessToken(),
    loginSuccessful: null
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

const loginReducer = createReducer(initialState, {
    [LOGIN_REQUEST]: setLoginRequested,
    [LOGIN_SUCCESS]: setLoginSucceed,
    [LOGIN_FAILED]: setLoginFailed
});

export {
    LOGIN_SUCCESS,
    performLogin,
};

export default loginReducer;