import React from 'react'
import {Redirect} from 'react-router-dom'
import {getAccessToken} from "../../utils/token";

const IndexPage = ({switchPage}) => {
    const token = getAccessToken();
    if (!token) {
        const path = "/login";
        switchPage(path);
        return <Redirect to={path}/>
    } else {
        const path = "/home";
        switchPage(path);
        return <Redirect to={path}/>
    }
};

export default IndexPage;