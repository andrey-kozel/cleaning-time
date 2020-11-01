import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import AppHeader from "../header/AppHeader";
import TokenRefresher from "../token-refresher";
import {Grid} from "@material-ui/core";

const useStyles = makeStyles(theme => ({
    mainContent: {
        marginTop: theme.spacing(10)
    }
}));

const AuthorizedTemplate = (props) => {
    const classes = useStyles();

    return (
        <>
            <AppHeader/>
            <Grid container className={classes.mainContent} justify="center">
                {props.children}
            </Grid>
            <TokenRefresher/>
        </>
    );
};

export default AuthorizedTemplate;