import {createReducer} from 'redux-create-reducer';

const SWITCH_PAGE = "cleaning-time/header/navigation/SWITCH_PAGE";

const switchPage = (path) => {
    return {
        type: SWITCH_PAGE,
        payload: path
    }
};

const initialState = {
    path: window.location.pathname
};

const setPath = (state, action) => {
    return {
        path: action.payload
    }
};

const reducer = createReducer(initialState,{
    [SWITCH_PAGE]: setPath
});

export {
    switchPage
}

export default reducer;
