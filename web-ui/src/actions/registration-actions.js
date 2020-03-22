import {REGISTER_USER, REGISTER_USER_FAILED, REGISTER_USER_SUCCESS} from "../action-types";

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

const performRegistration = (cleaningTimeService, dispatch) => (userDetails, history) => {
    dispatch(registerUser());
    cleaningTimeService.registerUser(userDetails)
        .then(response => dispatch(registerUserSuccess(response.data)))
        .then(() => history.push("/login"))
        .catch(error => dispatch(registerUserFailed(error.response)));
};

export default performRegistration;