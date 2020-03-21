import {LOGIN_FAILED, LOGIN_REQUEST, LOGIN_SUCCESS} from "../action-types";

const loginUser = () => {
    return {
        type: LOGIN_REQUEST,
    }
};

const loginUserSuccess = (credentials) => {
    return {
        type: LOGIN_SUCCESS,
        payload: credentials
    }
};

const loginUserFailed = (response) => {
    return {
        type: LOGIN_FAILED,
        payload: response
    }
};

const performLogin = (cleaningTimeService, dispatch) => (credentials) => {
    dispatch(loginUser());
    cleaningTimeService.loginUser(credentials)
        .then(response => dispatch(loginUserSuccess(response.data)))
        .catch(error => dispatch(loginUserFailed(error.response)));
};

export default performLogin;