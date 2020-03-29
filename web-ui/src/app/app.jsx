import React, {Fragment} from 'react'
import {CssBaseline} from "@material-ui/core";
import {Route, Switch} from 'react-router-dom'

import PrivateRoute from "../common/components/private-route";

import TokenRefresher from '../features/components/token-refresher'
import LoginPage from "../features/components/login-page";
import RegistrationPage from "../features/components/registration-page";
import HomePage from '../features/components/home-page'

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
                <PrivateRoute path={["/", "/home"]}
                              component={HomePage}
                              exact/>
            </Switch>
            <TokenRefresher />
        </Fragment>
    )
};

export default App;