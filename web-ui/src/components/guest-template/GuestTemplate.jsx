import React from "react";
import {Grid} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";

import Background from "../../resources/images/unauthorized-background.jpg";

const useStyles = makeStyles(() => ({
    loginBackground: {
        backgroundImage: `linear-gradient( rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5) ), url(${Background})`,
        backgroundSize: "cover",
        minHeight: "100vh",
        minWidth: "100%"
    }
}));

const GuestTemplate = (props) => {
    const classes = useStyles();

    return (
        <Grid
            className={classes.loginBackground}
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justify="center"
        >
            <Grid item xs={12} md={6} lg={3}>
                {props.children}
            </Grid>
        </Grid>
    );
};

export default GuestTemplate;