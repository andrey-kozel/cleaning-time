import {connect} from 'react-redux';
import {compose} from "redux";

import {withCleaningTimeService} from "../../../common/components/hoc";
import {getCommunities} from "./duck";
import Communities from "./Communities";

const mapStateToProps = (state) => {
    return {
        communities: state.communities.communities,
        loading: state.communities.loadingCommunities,
        failed: state.communities.communitiesRequestFailed
    }
};

const mapDispatchToProps = (dispatch, {cleaningTimeService}) => {
    return {
        getCommunities: getCommunities(cleaningTimeService, dispatch)
    }
};

export default compose(
    withCleaningTimeService(),
    connect(mapStateToProps, mapDispatchToProps),
)(Communities);