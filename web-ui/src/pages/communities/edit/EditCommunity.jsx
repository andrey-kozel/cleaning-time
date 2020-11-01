import React, {useEffect, useState} from "react";
import cleaningTimeService from "../../../api/CleaningTimeApi";
import CommunityForm from "../common/CommunityForm";

const EditCommunity = ({history, ...props}) => {
    const {id} = props.match.params;
    const [community, setCommunity] = useState({name: ""});

    useEffect(() => getCommunity(), [id]);

    const getCommunity = () => {
        cleaningTimeService.getCommunity(id)
            .then(response => setCommunity(response.data));
    };

    const saveCommunity = (values) => {
        cleaningTimeService.updateCommunity(id, values)
            .then(({data}) => setCommunity(data));
    };

    return (
        <CommunityForm community={community}
                       saveCommunity={saveCommunity}/>
    );
};

export default EditCommunity;