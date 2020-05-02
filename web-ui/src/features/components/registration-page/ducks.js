import {createSlice} from "@reduxjs/toolkit";
import cleaningTimeService from "../../../common/services/cleaning-time-service";

const registrationSlice = createSlice({
    name: 'registration',
    initialState: {
        registrationInProgress: false,
        registrationFailed: false
    },
    reducers: {
        registerUserRequested: (state) => {
            state.registrationInProgress = true;
            state.registrationFailed = false;
        },
        registerUserSucceed: (state) => {
            state.registrationInProgress = false;
            state.registrationFailed = false;
        },
        registerUserFailed: (state) => {
            state.registrationInProgress = false;
            state.registrationFailed = true;
        }
    }
})

const performRegistration = (userDetails, history) => dispatch => {
    dispatch(registrationSlice.actions.registerUserRequested());
    cleaningTimeService.registerUser(userDetails)
        .then(() => dispatch(registrationSlice.actions.registerUserSucceed()))
        .then(() => history.push("/login"))
        .catch(() => dispatch(registrationSlice.actions.registerUserFailed()));
};

export {
    performRegistration
};

export default registrationSlice.reducer;
