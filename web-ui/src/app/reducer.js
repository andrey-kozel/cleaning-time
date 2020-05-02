import {combineReducers} from 'redux'

import loginReducer from "../common/components/auth/ducks";
import navigationReducer from "../common/components/ui/header/navigation/ducks";
import registrationReducer from "../features/components/registration-page/ducks";
import refreshReducer from "../features/components/token-refresher/ducks";
import communitiesReducer from "../features/components/communities/list/ducks";
import communityReducer from "../features/components/communities/edit/ducks";

const reducer = combineReducers({
    login: loginReducer,
    registration: registrationReducer,
    refresh: refreshReducer,
    navigation: navigationReducer,
    communities:  communitiesReducer,
    community: communityReducer
});

export default reducer;