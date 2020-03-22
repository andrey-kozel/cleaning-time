import {applyMiddleware, createStore} from 'redux'

import reducer from './reducers'
import {LOGIN_SUCCESS} from "./action-types";
import {saveToken} from "./utils/token";

const saveTokenMiddleware = () => (dispatch) => (action) => {
    if (action.type === LOGIN_SUCCESS) {
        saveToken(action.payload.accessToken);
    }
    return dispatch(action);
};

const store = createStore(reducer, applyMiddleware(saveTokenMiddleware));

export default store;