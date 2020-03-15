import {
    LOGIN_FAILED,
    LOGIN_REQUEST,
    LOGIN_SUCCESS,
    REGISTER_USER,
    REGISTER_USER_FAILED,
    REGISTER_USER_SUCCESS
} from '../action-types';

const registerUser = () => {
    return {
        type: REGISTER_USER
    }
};

const registerUserSuccess = (tokenResponse) => {
    return {
        type: REGISTER_USER_SUCCESS,
        payload: tokenResponse
    }
};

const registerUserFailed = (response) => {
    return {
        type: REGISTER_USER_FAILED,
        payload: response
    }
};

const performRegistration = (cleaningTimeService, dispatch) => (userDetails) => {
    dispatch(registerUser());
    cleaningTimeService.registerUser(userDetails)
        .then(response => registerUserSuccess(response.data))
        .catch(error => registerUserFailed(error.response));
};

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

export {
    performRegistration,
    performLogin
}