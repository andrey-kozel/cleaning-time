import React, {Fragment} from 'react'
import {CssBaseline} from "@material-ui/core";
import {Route, Switch} from 'react-router-dom'

import PrivateRoute from "../common/components/private-route";

import TokenRefresher from '../features/components/token-refresher'
import LoginPage from "../features/components/login-page";
import RegistrationPage from "../features/components/registration-page";
import HomePage from '../features/components/home-page'
import AppHeader from "../features/components/header/AppHeaderContainer";
import Communities from "../features/components/communities/list/CommunitiesContainer";
import EditCommunity from "../features/components/communities/edit/EditCommunityContainer";
import Reports from "../features/components/reports/Reports";
import IndexPage from "../features/components/index-page";
import NotificationBar from "../common/components/notification/NotificationBarContainer";

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
                <PrivateRoute path="/communities"
                              component={Communities}
                              exact/>
                <PrivateRoute path="/community/:id?"
                              component={EditCommunity}
                              exact/>
                <PrivateRoute path="/reports"
                              component={Reports}
                              exact/>
            </Switch>
            <NotificationBar/>
            <TokenRefresher/>
        </Fragment>
    )
};

export default App;