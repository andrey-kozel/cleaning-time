import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux'
import {ThemeProvider} from '@material-ui/core/styles'
import {BrowserRouter as Router} from "react-router-dom";

import store from './app/store'
import ErrorBoundary from "./components/error-handling";
import App from './app/app';
import theme from './app/theme'

ReactDOM.render(
    <Provider store={store}>
        <ThemeProvider theme={theme}>
            <ErrorBoundary>
                <Router>
                    <App/>
                </Router>
            </ErrorBoundary>
        </ThemeProvider>
    </Provider>,
    document.getElementById('root'));
