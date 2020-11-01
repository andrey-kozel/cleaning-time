import React from 'react';

import {Button} from '@material-ui/core'

const LogoutButton = ({performLogout, history}) => {

    const onLogout = () => {
        performLogout(history);
    };

    return (
        <Button variant="contained" color="secondary" onClick={() => onLogout(history)}>
            Logout
        </Button>
    );
};

export default LogoutButton;