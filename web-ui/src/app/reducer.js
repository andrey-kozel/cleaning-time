import {combineReducers} from 'redux'

import loginReducer from "../common/components/auth/ducks";
import navigationReducer from "../features/components/header/naigation/ducks";
import registrationReducer from "../features/components/registration-page/ducks";
import refreshReducer from "../features/components/token-refresher/ducks";
import communitiesReducer from "../features/components/communities/list/ducks";
import communityReducer from "../features/components/communities/edit/ducks";
import notificationReducer from "../common/components/notification/ducks";

const reducer = combineReducers({
    login: loginReducer,
    registration: registrationReducer,
    refresh: refreshReducer,
    navigation: navigationReducer,
    communities:  communitiesReducer,
    community: communityReducer,
    notification: notificationReducer
});

export default reducer;