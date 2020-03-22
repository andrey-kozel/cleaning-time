import {LOGIN_FAILED, LOGIN_REQUEST, LOGIN_SUCCESS} from "../action-types";

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

export default performLogin;