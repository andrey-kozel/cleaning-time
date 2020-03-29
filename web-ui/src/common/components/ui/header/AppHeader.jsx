import React from 'react'
import {AppBar, Grid, Toolbar, Typography} from '@material-ui/core'
import {makeStyles} from '@material-ui/core/styles';
import LogoutButton from "./logout-button/LoginButtonContainer";

const useStyles = makeStyles((theme) => {

});

const AppHeader = () => {
    const classes = useStyles();
    return (
        <AppBar position="fixed">
            <Toolbar>
                <Grid container justify="space-between" direction="row">
                    <Grid container item xs={6}>
                        <Typography variant="h6" className={classes.title} color="secondary">
                            Cleaning time
                        </Typography>
                    </Grid>
                    <Grid container item xs={6} direction="row" justify="flex-end">
                        <LogoutButton />
                    </Grid>
                </Grid>
            </Toolbar>
        </AppBar>
    )
};

export default AppHeader;