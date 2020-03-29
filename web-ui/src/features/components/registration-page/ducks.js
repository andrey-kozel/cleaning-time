import {createReducer} from 'redux-create-reducer';

const REGISTER_USER = "cleaningTime/registration/REGISTER_USER";
const REGISTER_USER_SUCCESS = "cleaningTime/registration/REGISTER_USER_SUCCESS";
const REGISTER_USER_FAILED = "cleaningTime/registration/REGISTER_USER_FAILED";

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

const initialState = {
    registrationInProgress: false,
    registrationSuccessful: null
};

const setRegisterUserRequested = () => ({
    registrationInProgress: true,
    registrationSuccessful: null
});

const setUserRegistrationSucceed = () => ({
    registrationInProgress: false,
    registrationSuccessful: true
});

const setUserRegistrationFailed = () => ({
    registrationInProgress: false,
    registrationSuccessful: false
});

const registrationReducer = createReducer(initialState, {
    [REGISTER_USER]: setRegisterUserRequested,
    [REGISTER_USER_SUCCESS]: setUserRegistrationSucceed,
    [REGISTER_USER_FAILED]: setUserRegistrationFailed
});

export {
    performRegistration
};

export default registrationReducer;
