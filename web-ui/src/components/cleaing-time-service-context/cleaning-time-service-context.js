import React, {createContext} from 'react';

const {Provider: CleaningTimeServiceProvider, Consumer: CleaningTimeServiceConsumer} = createContext();

export {
    CleaningTimeServiceProvider,
    CleaningTimeServiceConsumer
}