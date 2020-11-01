import React, {Fragment} from "react";
import {CssBaseline} from "@material-ui/core";
import {Route, Switch} from "react-router-dom";

import PrivateRoute from "../components/private-route";
import TokenRefresher from "../components/token-refresher";
import AppHeader from "../components/header/AppHeaderContainer";
import NotificationBar from "../components/notification/NotificationBarContainer";

import LoginPage from "../pages/login-page";
import RegistrationPage from "../pages/registration-page";
import HomePage from "../pages/home-page";
import Communities from "../pages/communities/list/CommunitiesContainer";
import EditCommunity from "../pages/communities/edit/EditCommunityContainer";
import Reports from "../pages/reports/Reports";
import IndexPage from "../pages/index-page";

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
    );
};

export default App;