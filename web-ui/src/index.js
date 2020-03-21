import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux'
import {BrowserRouter as Router} from "react-router-dom";


import store from './store'
import ErrorBoundary from "./components/error-boundary/error-boundary";
import {CleaningTimeServiceProvider} from './components/cleaing-time-service-context'
import CleaningTimeService from "./services/cleaning-time-service";
import App from './components/app';

const cleaningTimeService = new CleaningTimeService();

ReactDOM.render(
    <Provider store={store}>
        <ErrorBoundary>
            <CleaningTimeServiceProvider value={cleaningTimeService}>
                <Router>
                    <App/>
                </Router>
            </CleaningTimeServiceProvider>
        </ErrorBoundary>
    </Provider>,
    document.getElementById('root'));
