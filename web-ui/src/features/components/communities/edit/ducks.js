import {createSlice} from "@reduxjs/toolkit";

import cleaningTimeService from '../../../../common/services/cleaning-time-service';
import {showSuccess, showError} from "../../../../common/components/notification/ducks";

const editCommunitySlice = createSlice({
    name: 'editCommunity',
    initialState: {
        community: {
            name: ''
        },
        loadingCommunity: false,
        loadingCommunityFailed: false,
        savingCommunity: false,
        savingCommunityFailed: false,
    },
    reducers: {
        saveCommunityRequested: (state) => {
            state.savingCommunity = true;
            state.savingCommunityFailed = false;
        },
        saveCommunitySucceed: (state, action) => {
            state.savingCommunity = false;
            state.community.name = action.payload.name;
        },
        saveCommunityFailed: (state) => {
            state.savingCommunity = false;
            state.savingCommunityFailed = true
        },
        updateCommunityRequested: (state, action) => {
            state.savingCommunity = true;
            state.savingCommunityFailed = false;
        },
        getCommunityRequested: (state) => {
            state.loadingCommunity = true;
            state.loadingCommunityFailed = false;
        },
        getCommunitySucceed: (state, action) => {
            state.community = action.payload;
            state.loadingCommunity = false;
            state.loadingCommunityFailed = false;
        },
        getCommunityFailed: (state, action) => {
            state.loadingCommunity = false;
            state.loadingCommunityFailed = true;
        },
        clearCommunity: (state) => {
            state.community.name = '';
        }
    }
});

const {
    saveCommunityRequested,
    saveCommunitySucceed,
    saveCommunityFailed,
    updateCommunityRequested,
    getCommunityRequested,
    getCommunitySucceed,
    getCommunityFailed
} = editCommunitySlice.actions

const saveCommunity = (community, history) => dispatch => {
    dispatch(saveCommunityRequested());
    cleaningTimeService.saveCommunity(community)
        .then(response => {
            dispatch(saveCommunitySucceed(response.data));
            dispatch(showSuccess("Community saved successfully"));
            history.push(`/community/${response.data.id}`);
        })
        .catch(error => {
            dispatch(saveCommunityFailed(error.response));
            dispatch(showError("Failed to save community"));
        });
};

const updateCommunity = (id, community) => dispatch => {
    dispatch(updateCommunityRequested());
    cleaningTimeService.updateCommunity(id, community)
        .then(response => {
            dispatch(saveCommunitySucceed(response.data));
            dispatch(showSuccess("Community updated successfully"));
        })
        .catch(error => {
            dispatch(saveCommunityFailed(error.response));
            dispatch(showError("Failed to update community"));
        });
}

const getCommunity = (communityId) => dispatch => {
    dispatch(getCommunityRequested());
    cleaningTimeService.getCommunity(communityId)
        .then(response => dispatch(getCommunitySucceed(response.data)))
        .catch(error => {
            dispatch(getCommunityFailed(error.response));
            dispatch(showError("Failed to get community"))
        });
};

export {
    getCommunity,
    saveCommunity,
    updateCommunity
}

export const {
    clearCommunity
} = editCommunitySlice.actions

export default editCommunitySlice.reducer;