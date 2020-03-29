import React from 'react';
import {Tab} from "@material-ui/core";
import {Link} from "react-router-dom";

const NavigationTab = ({name, path, switchPage, ...props}) => {
    const onLinkClick = () => {
        switchPage(path);
    };

    return (
        <Tab
            onClick={onLinkClick}
            {...props}
            component={Link}
            label={name}
            to={path}
        />
    )
};

export default NavigationTab;