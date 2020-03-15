import React from 'react'
import {CleaningTimeServiceConsumer} from "../cleaing-time-service-context";

const withCleaningTimeService = () => (Wrapped) => {

    return (props) => {
        return (
            <CleaningTimeServiceConsumer>
                {
                    (cleaningTimeService) => {
                        return <Wrapped {...props} cleaningTimeService={cleaningTimeService}/>
                    }
                }
            </CleaningTimeServiceConsumer>
        );
    }
};

export default withCleaningTimeService