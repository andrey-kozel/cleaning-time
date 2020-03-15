import {LOGIN_REQUEST, LOGIN_FAILED, REGISTER_USER} from '../action-types';

const initialState = {
    login: {
        accessToken: null,
        loginSuccessful: null
    }
};

const reducer = (state = initialState, action) => {
    console.log(action.payload);
    switch (action.type) {
        case LOGIN_REQUEST:
            return {
                ...state,
                login: {
                    loginSuccessful: true,
                    accessToken: action.payload
                }
            };
        case LOGIN_FAILED:
            return {
                ...state,
                login: {
                    loginSuccessful: false,
                    accessToken: null
                }
            };
        case REGISTER_USER:
            return {
                ...state,
                login: {
                    loginSuccessful: true,
                    accessToken: action.payload
                }
            };
        case 'REGISTER_USER_SUCCESSFUL':
            return state;
        case 'REGISTER_USER_FAILED':
            return state;
        default:
            return state;
    }
};

export default reducer;