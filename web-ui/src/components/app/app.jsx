import React, {Fragment} from 'react'
import {CssBaseline} from "@material-ui/core";
import {Route, Switch} from 'react-router-dom'

import PrivateRoute from "../private-route/PrivateRoute";
import LoginPage from "../login";
import RegistrationPage from "../registration";
import HomePage from '../home'

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
                <PrivateRoute path="/home"
                              component={HomePage}
                              exact/>
            </Switch>
        </Fragment>
    )
};

export default App;