import {applyMiddleware, createStore} from 'redux'

import reducer from './reducers'
import {LOGIN_SUCCESS} from "./ducks/login";
import {REFRESH_SUCCESS} from "./ducks/token-refresh";
import {saveToken} from "./utils/token";

const saveTokenMiddleware = () => (dispatch) => (action) => {
    switch (action.type) {
        case REFRESH_SUCCESS:
        case LOGIN_SUCCESS:
            saveToken(action.payload.accessToken);
            return dispatch(action);
        default:
            return dispatch(action);
    }
};

const store = createStore(reducer, applyMiddleware(saveTokenMiddleware));

export default store;