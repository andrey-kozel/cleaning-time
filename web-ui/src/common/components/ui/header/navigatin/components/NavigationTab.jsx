import React from 'react';
import {Tab} from "@material-ui/core";
import {Link} from "react-router-dom";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles(() => ({
    headerLink: {
        textTransform: 'capitalize'
    }
}));

const NavigationTab = ({name, path, ...props}) => {
    const classes = useStyles();

    return (
        <Tab
            className={classes.headerLink}
            {...props}
            component={Link}
            label={name}
            to={path}
        />
    )
};

export default NavigationTab;