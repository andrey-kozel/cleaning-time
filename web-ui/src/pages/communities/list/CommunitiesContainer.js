import {connect} from 'react-redux';
import {compose} from "redux";
import {deleteCommunity, getCommunities} from "./ducks";
import Communities from "./Communities";

const mapStateToProps = (state) => {
    return {
        communities: state.communities.communities,
        loading: state.communities.loadingCommunities,
        failed: state.communities.communitiesRequestFailed
    }
};

const mapDispatchToProps = {
    getCommunities,
    deleteCommunity
};

export default compose(
    connect(mapStateToProps, mapDispatchToProps),
)(Communities);