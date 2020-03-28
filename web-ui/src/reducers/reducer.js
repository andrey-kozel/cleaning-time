import {combineReducers} from 'redux'
import loginReducer from "../ducks/login";
import registrationReducer from "../ducks/registration";
import refreshReducer from "../ducks/token-refresh";

const reducer = combineReducers({
    login: loginReducer,
    registration: registrationReducer,
    refresh: refreshReducer
});

export default reducer;