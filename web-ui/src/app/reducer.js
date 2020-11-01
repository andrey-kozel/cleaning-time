import {combineReducers} from "redux";

import loginReducer from "../components/auth/ducks";
import navigationReducer from "../components/header/naigation/ducks";
import registrationReducer from "../pages/registration-page/ducks";
import refreshReducer from "../components/token-refresher/ducks";
import notificationReducer from "../components/notification/ducks";

const reducer = combineReducers({
    login: loginReducer,
    registration: registrationReducer,
    refresh: refreshReducer,
    navigation: navigationReducer,
    notification: notificationReducer
});

export default reducer;