import {combineReducers} from 'redux'

import loginReducer from "../common/components/auth/ducks";
import navigationReducer from "../common/components/ui/header/navigatin/ducks";
import registrationReducer from "../features/components/registration-page/ducks";
import refreshReducer from "../features/components/token-refresher/ducks";

const reducer = combineReducers({
    login: loginReducer,
    registration: registrationReducer,
    refresh: refreshReducer,
    navigation: navigationReducer
});

export default reducer;