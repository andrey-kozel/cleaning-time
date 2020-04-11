import React, {useEffect, useState} from 'react';
import {useLocation} from 'react-router-dom'

import {Tabs} from '@material-ui/core';
import NavigationTab from "./components";

const links = [
    {
        name: "Home",
        path: "/home"
    },
    {
        name: "Groups",
        path: "/groups"
    },
    {
        name: "Reports",
        path: "/reports"
    }
];

const HeaderNavigation = () => {
    const [selectedTab, setSelectedTab] = useState(0);
    const location = useLocation();

    useEffect(() => {
        const tabIndex = links.findIndex(link => link.path.startsWith(window.location.pathname));
        setSelectedTab(tabIndex === -1 ? 0 : tabIndex);
    }, [location.pathname]);


    return (
        <Tabs value={selectedTab}
              indicatorColor="primary"
        >
            {
                links.map(link => <NavigationTab key={link.name}
                                                 name={link.name}
                                                 path={link.path}/>)
            }
        </Tabs>
    )
};

export default HeaderNavigation;