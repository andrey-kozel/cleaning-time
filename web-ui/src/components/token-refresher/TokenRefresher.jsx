import React, {useEffect} from 'react'
import {compose} from "redux";
import {connect} from "react-redux";

import {withCleaningTimeService} from "../hoc";
import {isTokenAlmostExpired, performRefresh} from '../../ducks/token-refresh'

const TokenRefresher = ({performRefresh, isTokenAlmostExpired}) => {
    const refreshIfAlmostExpired = () => {
        if (isTokenAlmostExpired()) {
            performRefresh()
        }
    };

    useEffect(() => {
        const intervalId = setInterval(refreshIfAlmostExpired, 20000);
        return () => clearInterval(intervalId);
    }, [refreshIfAlmostExpired]);

    return (
        <span/>
    )
};

const mapDispatchToProps = (dispatch, {cleaningTimeService}) => {
    return {
        performRefresh: performRefresh(cleaningTimeService, dispatch),
        isTokenAlmostExpired: isTokenAlmostExpired
    };
};

export default compose(
    withCleaningTimeService(),
    connect(null, mapDispatchToProps),
)(TokenRefresher);