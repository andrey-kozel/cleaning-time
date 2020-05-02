import React from 'react'
import {Redirect, Route} from 'react-router-dom'
import {getAccessToken} from "../../../../utils/token";

const PrivateRoute = ({component: Component, ...other}) => {

    const renderIfAuthorized = (props) => {
        const token = getAccessToken();
        if (!token) {
            return <Redirect to="/login"/>
        } else {
            return <Component {...props}/>
        }
    };

    return (
        <Route
            {...other}
            render={renderIfAuthorized}/>
    )
};

export default PrivateRoute