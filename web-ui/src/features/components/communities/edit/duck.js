import {createReducer} from 'redux-create-reducer';

const UPDATE_COMMUNITY_REQUEST = "cleaningTime/community/UPDATE_COMMUNITY_REQUEST"
const SAVE_COMMUNITY_REQUEST = "cleaningTime/community/SAVE_COMMUNITY"
const SAVE_COMMUNITY_SUCCEED = "cleaningTime/community/SAVE_COMMUNITY_SUCCEED"
const SAVE_COMMUNITY_FAILED = "cleaningTime/community/SAVE_COMMUNITY_FAILED"
const GET_COMMUNITY_REQUEST = "cleaningTime/community/GET_COMMUNITY"
const GET_COMMUNITY_SUCCEED = "cleaningTime/community/GET_COMMUNITY_SUCCEED"
const GET_COMMUNITY_FAILED = "cleaningTime/community/GET_COMMUNITY_FAILED"

const saveCommunityRequested = () => {
    return {
        type: SAVE_COMMUNITY_REQUEST,
    }
};

const saveCommunitySucceed = (community) => {
    return {
        type: SAVE_COMMUNITY_SUCCEED,
        payload: community
    }
};

const saveCommunityFailed = (error) => {
    return {
        type: SAVE_COMMUNITY_FAILED,
        payload: error
    }
};

const saveCommunity = (cleaningTimeService, dispatch) => (community, history) => {
    dispatch(saveCommunityRequested());
    cleaningTimeService.saveCommunity(community)
        .then(response => {
            dispatch(saveCommunitySucceed(response.data))
            history.push(`/community/${response.data.id}`)
        })
        .catch(error => dispatch(saveCommunityFailed(error.response)));
};

const updateCommunityRequested = () => {
    return {
        type: UPDATE_COMMUNITY_REQUEST
    }
}

const updateCommunity = (cleaningTimeService, dispatch) => (id, community) => {
    dispatch(updateCommunityRequested());
    cleaningTimeService.updateCommunity(id, community)
        .then(response => dispatch(saveCommunitySucceed(response.data)))
        .catch(error => dispatch(saveCommunityFailed(error.response)));
}

const getCommunityRequested = () => {
    return {
        type: GET_COMMUNITY_REQUEST
    }
};

const getCommunitySucceed = (community) => {
    return {
        type: GET_COMMUNITY_SUCCEED,
        payload: community
    }
};

const getCommunityFailed = () => {
    return {
        type: GET_COMMUNITY_FAILED
    }
};

const getCommunity = (cleaningTimeService, dispatch) => (communityId) => {
    dispatch(getCommunityRequested());
    cleaningTimeService.getCommunity(communityId)
        .then(response => dispatch(getCommunitySucceed(response.data)))
        .catch(error => dispatch(getCommunityFailed(error.response)));
};

const initialState = {
    community: {
        name: ''
    },
    loadingCommunity: false,
    loadingCommunityFailed: false,
    savingCommunity: false,
    savingCommunityFailed: false,
};

const setSaveCommunityRequested = (state) => ({
    ...state,
    savingCommunity: true,
    savingCommunityFailed: false
});

const setSaveCommunitySucceed = (state, community) => ({
    ...state,
    savingCommunity: false,
    community: {
        name: community.name
    }
});

const setSaveCommunityFailed = (state) => ({
    ...state,
    savingCommunity: false,
    savingCommunityFailed: true
});

const setCommunityRequested = (state) => ({
    ...state,
    loadingCommunity: true,
    loadingCommunityFailed: false,
});

const setCommunitySucceed = (state, {payload}) => ({
    ...state,
    community: payload,
    loadingCommunity: false,
    loadingCommunityFailed: false,
});

const setCommunityFailed = (state) => ({
    ...state,
    loadingCommunity: false,
    loadingCommunityFailed: true,
});

const communityReducer = createReducer(initialState, {
    [SAVE_COMMUNITY_REQUEST]: setSaveCommunityRequested,
    [SAVE_COMMUNITY_SUCCEED]: setSaveCommunitySucceed,
    [SAVE_COMMUNITY_FAILED]: setSaveCommunityFailed,
    [GET_COMMUNITY_REQUEST]: setCommunityRequested,
    [GET_COMMUNITY_SUCCEED]: setCommunitySucceed,
    [GET_COMMUNITY_FAILED]: setCommunityFailed
});

export {
    getCommunity,
    saveCommunity,
    updateCommunity
}

export default communityReducer;