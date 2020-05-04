import {createSlice} from "@reduxjs/toolkit";

const notificationSlice = createSlice({
    name: 'notifications',
    initialState: {
        message: '',
        severity: 'success',
        opened: false
    },
    reducers: {
        showError: (state, action) => {
            state.message = action.payload;
            state.severity = 'error';
            state.opened = true;
        },
        showSuccess: (state, action) => {
            state.message = action.payload;
            state.severity = 'success';
            state.opened = true;
        },
        showInfo: (state, action) => {
            state.message = action.payload;
            state.severity = 'info';
            state.opened = true;
        },
        hideNotification: (state) => {
            state.opened = false;
            state.message = '';
        }
    }
});

export const {
    showError,
    showSuccess,
    hideNotification
} = notificationSlice.actions

export default notificationSlice.reducer;