import React, {Fragment} from 'react'
import {Route, Switch} from 'react-router-dom'

import LoginPage from "../login";
import {CssBaseline} from "@material-ui/core";
import RegistrationPage from "../registration";

const App = () => {
    return (
        <Fragment>
            <CssBaseline/>
            <Switch>
                <Route path="/login"
                       component={LoginPage}
                       exact/>
                <Route path="/registration"
                       component={RegistrationPage}
                       exact/>
            </Switch>
        </Fragment>
    )
};

export default App;