import {applyMiddleware, createStore} from 'redux'

import reducer from './reducer'
import {saveToken, cleanUpToken} from "../common/utils/token";

import {LOGIN_SUCCESS, LOGOUT} from "../common/components/auth/ducks";
import {REFRESH_SUCCESS} from "../features/components/token-refresher/ducks";


const saveTokenMiddleware = () => (dispatch) => (action) => {
    console.log(action);
    switch (action.type) {
        case REFRESH_SUCCESS:
        case LOGIN_SUCCESS:
            saveToken(action.payload.accessToken);
            return dispatch(action);
        case LOGOUT:
            cleanUpToken();
            return dispatch(action);
        default:
            return dispatch(action);
    }
};

const store = createStore(reducer, applyMiddleware(saveTokenMiddleware));

export default store;