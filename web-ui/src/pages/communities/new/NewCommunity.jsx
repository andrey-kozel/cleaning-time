import React from "react";
import cleaningTimeService from "../../../api/CleaningTimeApi";
import CommunityForm from "../common/CommunityForm";

const NewCommunity = ({history}) => {

    const saveCommunity = (values) => {
        cleaningTimeService.saveCommunity(values)
            .then(({data}) => history.push(`community/${data.id}`));
    };

    return (
        <CommunityForm community={{name: ""}}
                       saveCommunity={saveCommunity}/>
    );
};

export default NewCommunity;