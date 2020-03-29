import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux'
import {ThemeProvider} from '@material-ui/core/styles'
import {BrowserRouter as Router} from "react-router-dom";


import store from './app/store'
import ErrorBoundary from "./common/components/ui/errors";
import {CleaningTimeServiceProvider} from './common/components/contexts'
import CleaningTimeService from "./common/services/cleaning-time-service";
import App from './app';
import theme from './app/theme'

const cleaningTimeService = new CleaningTimeService();

ReactDOM.render(
    <Provider store={store}>
        <ThemeProvider theme={theme}>
            <ErrorBoundary>
                <CleaningTimeServiceProvider value={cleaningTimeService}>
                    <Router>
                        <App/>
                    </Router>
                </CleaningTimeServiceProvider>
            </ErrorBoundary>
        </ThemeProvider>
    </Provider>,
    document.getElementById('root'));
