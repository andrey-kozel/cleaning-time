import {combineReducers} from 'redux'

import loginReducer from "../features/components/login-page/ducks";
import registrationReducer from "../features/components/registration-page/ducks";
import refreshReducer from "../features/components/token-refresher/ducks";

const reducer = combineReducers({
    login: loginReducer,
    registration: registrationReducer,
    refresh: refreshReducer
});

export default reducer;