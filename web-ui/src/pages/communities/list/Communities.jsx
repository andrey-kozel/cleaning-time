import React, {useEffect, useState} from "react";
import {
    Card,
    CardContent,
    CardHeader,
    Grid,
    IconButton,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow
} from "@material-ui/core";
import AddCircleSharpIcon from "@material-ui/icons/AddCircleSharp";
import HighlightOffIcon from "@material-ui/icons/HighlightOff";
import CreateIcon from "@material-ui/icons/Create";
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";
import AuthorizedTemplate from "../../../components/authorized-template/AuthorizedTemplate";
import cleaningTimeService from "../../../api/CleaningTimeApi";

const useStyles = makeStyles(() => ({
    communitiesContent: {
        padding: 0
    }
}));

const Communities = () => {
    const classes = useStyles();
    const [communities, setCommunities] = useState({items: []});

    useEffect(() => getCommunities(), []);

    const getCommunities = () => {
        cleaningTimeService.getCommunities()
            .then(({data}) => setCommunities(data));
    };

    const deleteCommunity = (communityId) => {
        cleaningTimeService.deleteCommunity(communityId)
            .then(getCommunities);
    };

    return (
        <AuthorizedTemplate>
            <Grid item xs={11} md={8} lg={6}>
                <Card>
                    <CardHeader title="Communities" action={<AddCommunityButton/>}/>
                    <CardContent className={classes.communitiesContent}>
                        <TableContainer>
                            <Table>
                                <TableHead>
                                    <TableRow>
                                        <TableCell>
                                            Index
                                        </TableCell>
                                        <TableCell>
                                            Name
                                        </TableCell>
                                        <TableCell>
                                            Actions
                                        </TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {communities.items.map((community, idx) => <CommunitiesRow key={community.name}
                                                                                               onDelete={() => deleteCommunity(community.id)}
                                                                                               index={idx}
                                                                                               community={community}/>)}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </CardContent>
                </Card>
            </Grid>
        </AuthorizedTemplate>
    );
};

const CommunitiesRow = ({index, community, onDelete}) => {
    return (
        <TableRow>
            <TableCell>
                {index + 1}
            </TableCell>
            <TableCell>
                {community.name}
            </TableCell>
            <TableCell>
                <IconButton component={Link} to={`/community/${community.id}`}>
                    <CreateIcon/>
                </IconButton>
                <IconButton onClick={onDelete}>
                    <HighlightOffIcon/>
                </IconButton>
            </TableCell>
        </TableRow>
    );
};

const AddCommunityButton = () => {
    return (
        <IconButton component={Link} to="/community">
            <AddCircleSharpIcon/>
        </IconButton>
    );
};

export default Communities;