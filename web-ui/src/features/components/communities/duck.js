import {createReducer} from 'redux-create-reducer';

const FETCH_COMMUNITIES = "cleaningTime/communities/FETCH_COMMUNITIES";
const FETCH_COMMUNITIES_SUCCESS = "cleaningTime/communities/FETCH_COMMUNITIES_SUCCESS";
const FETCH_COMMUNITIES_FAILED = "cleaningTime/communities/FETCH_COMMUNITIES_FAILED";

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

const initialState = {
    communities: {
        items: [],
        total: 0
    },
    loadingCommunities: false,
    communitiesRequestFailed: false
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

const communitiesReducer = createReducer(initialState, {
    [FETCH_COMMUNITIES]: setCommunitiesRequested,
    [FETCH_COMMUNITIES_SUCCESS]: setCommunitiesRequestSucceed,
    [FETCH_COMMUNITIES_FAILED]: setCommunitiesRequestFailed
});

export {
    getCommunities
};

export default communitiesReducer;
