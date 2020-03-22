import React from 'react'
import {AppBar, Toolbar, Typography} from '@material-ui/core'
import {makeStyles} from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => {

});

const AppHeader = () => {
    const classes = useStyles();
    return (
        <AppBar position="fixed">
            <Toolbar>
                <Typography variant="h6" className={classes.title} color="secondary">
                    Cleaning time
                </Typography>
            </Toolbar>
        </AppBar>
    )
};

export default AppHeader;