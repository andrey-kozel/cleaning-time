import React, {useCallback, useEffect} from 'react'
import {compose} from "redux";
import {connect} from "react-redux";
import {performRefresh} from './ducks'

const TokenRefresher = ({performRefresh}) => {
    const refreshIfAlmostExpired = useCallback(() => {
        performRefresh()
    }, [performRefresh]);

    useEffect(() => {
        const intervalId = setInterval(refreshIfAlmostExpired, 20000);
        return () => clearInterval(intervalId);
    }, [refreshIfAlmostExpired]);

    return (
        <span/>
    )
};

const mapDispatchToProps = {
    performRefresh
};

export default compose(
    connect(null, mapDispatchToProps),
)(TokenRefresher);