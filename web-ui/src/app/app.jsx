import React, {Fragment} from "react";
import {CssBaseline} from "@material-ui/core";
import {Route, Switch} from "react-router-dom";

import PrivateRoute from "../components/private-route";
import NotificationBar from "../components/notification/NotificationBarContainer";

import LoginPage from "../pages/login-page";
import RegistrationPage from "../pages/registration-page";
import HomePage from "../pages/home-page";
import Communities from "../pages/communities/list/Communities";
import EditCommunity from "../pages/communities/edit/EditCommunity";
import Reports from "../pages/reports/Reports";
import NewCommunity from "../pages/communities/new/NewCommunity";

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
                <PrivateRoute path="/communities"
                              component={Communities}
                              exact/>
                <PrivateRoute path="/community/:id"
                              component={EditCommunity}
                              exact/>
                <PrivateRoute path="/community"
                              component={NewCommunity}
                              exact/>
                <PrivateRoute path="/reports"
                              component={Reports}
                              exact/>
            </Switch>
            <NotificationBar/>
        </Fragment>
    );
};

export default App;