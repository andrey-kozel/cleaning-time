import {
    LOGIN_FAILED,
    LOGIN_REQUEST,
    LOGIN_SUCCESS,
    REGISTER_USER,
    REGISTER_USER_FAILED,
    REGISTER_USER_SUCCESS
} from '../action-types';
import {getAccessToken} from "../utils/token";

const initialState = {
    login: {
        loginInProgress: false,
        accessToken: getAccessToken(),
        loginSuccessful: null
    },
    registration: {
        registrationInProgress: false,
        loginSuccessful: null
    }
};

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case LOGIN_REQUEST:
            return {
                ...state,
                login: {
                    loginInProgress: true,
                    loginSuccessful: null,
                    accessToken: null
                }
            };
        case LOGIN_SUCCESS:
            return {
                ...state,
                login: {
                    loginInProgress: false,
                    loginSuccessful: true,
                    accessToken: action.payload
                }
            };
        case LOGIN_FAILED:
            return {
                ...state,
                login: {
                    loginInProgress: false,
                    loginSuccessful: false,
                    accessToken: null
                }
            };
        case REGISTER_USER:
            return {
                ...state,
                registration: {
                    registrationSuccessful: null,
                    registrationInProgress: true
                }
            };
        case REGISTER_USER_SUCCESS:
            return {
                ...state,
                registration: {
                    registrationSuccessful: true,
                    registrationInProgress: false
                }
            };
        case REGISTER_USER_FAILED:
            return {
                ...state,
                registration: {
                    registrationSuccessful: false,
                    registrationInProgress: false
                }
            };
        default:
            return state;
    }
};

export default reducer;