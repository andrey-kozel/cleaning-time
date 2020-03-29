import React from 'react';

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

const HeaderNavigation = ({path, switchPage}) => {
    const selectedTab = links.findIndex(link => link.path === path);

    return (
        <Tabs value={selectedTab}
              indicatorColor="primary"
        >
            {
                links.map(link => <NavigationTab key={link.name}
                                                 name={link.name}
                                                 path={link.path}
                                                 switchPage={switchPage}/>)
            }
        </Tabs>
    )
};

export default HeaderNavigation;