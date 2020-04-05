import React from 'react'
import {AppBar, Grid, Toolbar, Typography} from '@material-ui/core'
import LogoutButton from "../../auth/components/logout-button/LoginButtonContainer";
import HeaderNavigation from "./navigatin/HeaderNavigation";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    toolbarMargin: {
        ...theme.mixins.toolbar
    }
}));

const AppHeader = ({accessToken}) => {
    const classes = useStyles();

    if (!accessToken) {
        return null;
    }

    return (
        <>
            <AppBar position="fixed">
                <Toolbar>
                    <Grid container justify="space-between" direction="row">
                        <Grid container item xs={2} justify="flex-start" alignContent="center">
                            <Typography variant="h6" color="secondary">
                                Cleaning time
                            </Typography>
                        </Grid>
                        <Grid container item xs={8}>
                            <HeaderNavigation/>
                        </Grid>
                        <Grid container item xs={2} direction="row" justify="flex-end">
                            <LogoutButton/>
                        </Grid>
                    </Grid>
                </Toolbar>
            </AppBar>
            <div className={classes.toolbarMargin}/>
        </>
    )
};

export default AppHeader;