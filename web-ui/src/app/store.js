import {configureStore, getDefaultMiddleware} from "@reduxjs/toolkit";
import reducer from "./reducer";
import {cleanUpToken, saveToken} from "../utils/token";

import {LOGIN_SUCCESS, LOGOUT} from "../components/auth/ducks";
import {REFRESH_SUCCESS} from "../components/token-refresher/ducks";


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

const store = configureStore({
    reducer,
    middleware: [...getDefaultMiddleware(), saveTokenMiddleware]
});

export default store;