import React from "react";
import {Slide, Snackbar, Typography} from "@material-ui/core";
import {Alert, AlertTitle} from "@material-ui/lab";

const NotificationBar = ({message, severity, opened, hideNotification}) => {
    return (
        <Snackbar open={opened}
                  autoHideDuration={4000}
                  onClose={() => hideNotification()}
                  TransitionComponent={(props) => <Slide {...props} direction="right"/>}
                  anchorOrigin={{
                      vertical: 'bottom',
                      horizontal: 'right'
                  }}
        >
            <Alert onClose={hideNotification}
                   severity={severity}
                   elevation={6}
                   variant="filled"
            >
                <AlertTitle>
                    <Typography>
                        {severity}
                    </Typography>
                </AlertTitle>
                {message}
            </Alert>
        </Snackbar>
    );
};

export default NotificationBar;