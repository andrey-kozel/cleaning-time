import {createSlice} from "@reduxjs/toolkit";
import cleaningTimeService from '../../../../common/services/cleaning-time-service';

const communitiesSlice = createSlice({
    name: "communities",
    initialState: {
        communities: {
            items: [],
            total: 0
        },
        loadingCommunities: false,
        communitiesRequestFailed: false,
        deletingCommunity: false,
        deleteCommunityFailed: false
    },
    reducers: {
        getCommunitiesRequested: (state) => {
            state.loadingCommunities = true;
            state.communitiesRequestFailed = false;
        },
        getCommunitiesSucceed: (state, action) => {
            state.communities = action.payload;
            state.loadingCommunities = false;
            state.communitiesRequestFailed = false;
        },
        getCommunitiesFailed: (state) => {
            state.loadingCommunities = false;
            state.communitiesRequestFailed = true;
        },
        deleteCommunityRequested: (state) => {
            state.deletingCommunity = true;
            state.deleteCommunityFailed = false;
        },
        deleteCommunitySucceed: (state) => {
            state.deletingCommunity = false;
            state.deleteCommunityFailed = false;
        },
        deleteCommunityFailed: (state) => {
            state.deletingCommunity = false;
            state.deleteCommunityFailed = true;
        }
    }
});

const {
    getCommunitiesRequested,
    getCommunitiesSucceed,
    getCommunitiesFailed,
    deleteCommunityRequested,
    deleteCommunitySucceed,
    deleteCommunityFailed
} = communitiesSlice.actions;

const getCommunities = () => dispatch => {
    dispatch(getCommunitiesRequested());
    cleaningTimeService.getCommunities()
        .then(({data}) => dispatch(getCommunitiesSucceed(data)))
        .catch(error => dispatch(getCommunitiesFailed(error.response)));
};

const deleteCommunity = (id) => dispatch => {
    dispatch(deleteCommunityRequested())
    cleaningTimeService.deleteCommunity(id)
        .then(() => {
            dispatch(deleteCommunitySucceed());
            dispatch(getCommunities());
        })
        .catch(() => dispatch(deleteCommunityFailed()))
};

export {
    getCommunities,
    deleteCommunity
};

export default communitiesSlice.reducer;
