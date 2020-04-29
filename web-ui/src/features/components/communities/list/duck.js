import {createReducer} from 'redux-create-reducer';

const FETCH_COMMUNITIES = "cleaningTime/communities/FETCH_COMMUNITIES";
const FETCH_COMMUNITIES_SUCCESS = "cleaningTime/communities/FETCH_COMMUNITIES_SUCCESS";
const FETCH_COMMUNITIES_FAILED = "cleaningTime/communities/FETCH_COMMUNITIES_FAILED";

const DELETE_COMMUNITY_REQUESTED = "cleaningTime/communities/DELETE_COMMUNITY_REQUESTED";
const DELETE_COMMUNITY_SUCCEED = "cleaningTime/communities/DELETE_COMMUNITY_SUCCESS";
const DELETE_COMMUNITY_FAILED = "cleaningTime/communities/DELETE_COMMUNITY_FAILED";

const fetchCommunities = () => {
    return {
        type: FETCH_COMMUNITIES
    }
};

const fetchCommunitiesSuccess = (communities) => {
    return {
        type: FETCH_COMMUNITIES_SUCCESS,
        payload: communities
    }
};

const fetchCommunitiesFailed = (response) => {
    return {
        type: FETCH_COMMUNITIES_FAILED,
        payload: response
    }
};

const getCommunities = (cleaningTimeService, dispatch) => () => {
    dispatch(fetchCommunities());
    cleaningTimeService.getCommunities()
        .then(({data}) => dispatch(fetchCommunitiesSuccess(data)))
        .catch(error => dispatch(fetchCommunitiesFailed(error.response)));
};

const deleteCommunityRequested = () => {
    return {
        type: DELETE_COMMUNITY_REQUESTED
    }
}

const deleteCommunitySucceed = () => {
    return {
        type: DELETE_COMMUNITY_SUCCEED
    }
}

const deleteCommunityFailed = () => {
    return {
        type: DELETE_COMMUNITY_FAILED
    }
}

const deleteCommunity = (cleaningTimeService, dispatch) => (id) => {
    dispatch(deleteCommunityRequested())
    cleaningTimeService.deleteCommunity(id)
        .then(() => {
            dispatch(deleteCommunitySucceed())
            getCommunities(cleaningTimeService, dispatch)()
        })
        .catch(() => dispatch(deleteCommunityFailed()))
}

const initialState = {
    communities: {
        items: [],
        total: 0
    },
    loadingCommunities: false,
    communitiesRequestFailed: false,
    deletingCommunity: false,
    deleteCommunityFailed: false

};

const setCommunitiesRequested = (state) => ({
    communities: state.communities,
    loadingCommunities: true,
    communitiesRequestFailed: false
});

const setCommunitiesRequestSucceed = (state, action) => ({
    communities: action.payload,
    loadingCommunities: false,
    communitiesRequestFailed: false
});

const setCommunitiesRequestFailed = (state) => ({
    communities: state.communities,
    loadingCommunities: false,
    communitiesRequestFailed: true
});

const setDeleteCommunityRequested = (state) => ({
    ...state,
    deletingCommunity: true,
    deleteCommunityFailed: false
});

const setDeleteCommunitySucceed = (state) => ({
    ...state,
    deletingCommunity: false,
    deleteCommunityFailed: false
});

const setDeleteCommunityFailed = (state) => ({
    ...state,
    deletingCommunity: false,
    deleteCommunityFailed: true
});

const communitiesReducer = createReducer(initialState, {
    [FETCH_COMMUNITIES]: setCommunitiesRequested,
    [FETCH_COMMUNITIES_SUCCESS]: setCommunitiesRequestSucceed,
    [FETCH_COMMUNITIES_FAILED]: setCommunitiesRequestFailed,
    [DELETE_COMMUNITY_REQUESTED]: setDeleteCommunityRequested,
    [DELETE_COMMUNITY_SUCCEED]: setDeleteCommunitySucceed,
    [DELETE_COMMUNITY_FAILED]: setDeleteCommunityFailed
});

export {
    getCommunities,
    deleteCommunity
};

export default communitiesReducer;
