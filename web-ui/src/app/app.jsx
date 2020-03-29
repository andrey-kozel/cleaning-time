import React, {Fragment} from 'react'
import {CssBaseline} from "@material-ui/core";
import {Route, Switch} from 'react-router-dom'

import PrivateRoute from "../common/components/private-route";

import TokenRefresher from '../features/components/token-refresher'
import LoginPage from "../features/components/login-page";
import RegistrationPage from "../features/components/registration-page";
import HomePage from '../features/components/home-page'
import AppHeader from "../common/components/ui/header/AppHeaderContainer";
import Groups from "../features/components/groups/Groups";
import Reports from "../features/components/reports/Reports";
import IndexPage from "../features/components/index-page";

const App = () => {
    return (
        <Fragment>
            <CssBaseline/>
            <AppHeader/>
            <Switch>
                <Route path="/login"
                       component={LoginPage}
                       exact/>
                <Route path="/registration"
                       component={RegistrationPage}
                       exact/>
                <Route path="/"
                       component={IndexPage}
                       exact/>
                <PrivateRoute path="/home"
                              component={HomePage}
                              exact/>
                <PrivateRoute path="/groups"
                              component={Groups}
                              exact/>
                <PrivateRoute path="/reports"
                              component={Reports}
                              exact/>
            </Switch>
            <TokenRefresher/>
        </Fragment>
    )
};

export default App;