import {createSlice} from "@reduxjs/toolkit";

const navigationSlice = createSlice({
    name: 'navigation',
    initialState: {
        path: window.location.pathname
    },
    reducers: {
        switchPage: (state, action) => {
            state.path = action.payload;
        }
    }
})

export const {
    switchPage
} = navigationSlice.actions

export default navigationSlice.reducer;
