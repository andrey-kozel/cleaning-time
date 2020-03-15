import React, {Fragment} from 'react'
import {Route, Switch} from 'react-router-dom'

import LoginPage from "../login";
import {CssBaseline} from "@material-ui/core";

const App = () => {
    return (
        <Fragment>
            <CssBaseline/>
            <Switch>
                <Route path="/"
                       component={LoginPage}
                       exact/>
            </Switch>
        </Fragment>
    )
};

export default App;