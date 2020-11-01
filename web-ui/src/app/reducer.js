import {combineReducers} from "redux";

import loginReducer from "../components/auth/ducks";
import navigationReducer from "../components/header/naigation/ducks";
import registrationReducer from "../pages/registration-page/ducks";
import refreshReducer from "../components/token-refresher/ducks";
import communitiesReducer from "../pages/communities/list/ducks";
import communityReducer from "../pages/communities/edit/ducks";
import notificationReducer from "../components/notification/ducks";

const reducer = combineReducers({
    login: loginReducer,
    registration: registrationReducer,
    refresh: refreshReducer,
    navigation: navigationReducer,
    communities: communitiesReducer,
    community: communityReducer,
    notification: notificationReducer
});

export default reducer;