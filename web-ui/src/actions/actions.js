import {LOGIN_USER, LOGIN_USER_FAILED, REGISTER_USER} from '../action-types';

const registerUser = (userDetails) => {
    return {
        type: REGISTER_USER,
        payload: userDetails
    }
};

const loginUser = (credentials) => {
    return {
        type: LOGIN_USER,
        payload: credentials
    }
};

const loinUserFailed = (response) => {
    return {
        type: LOGIN_USER_FAILED,
        payload: response
    }
};

export {
    registerUser,
    loginUser,
    loinUserFailed
}