import React, {useEffect} from 'react';
import {
    Card,
    CardContent,
    CardHeader,
    Grid,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow
} from '@material-ui/core';
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    mainContent: {
        marginTop: theme.spacing(10)
    },
    communitiesContent: {
        padding: 0
    }
}));

const CommunitiesRow = ({community}) => {
    return (
        <TableRow>

        </TableRow>
    );
};

const Communities = ({communities, getCommunities}) => {
    const classes = useStyles();

    useEffect(getCommunities, []);

    return (
        <Grid container className={classes.mainContent} justify="center">
            <Grid item xs={11} md={8} lg={6}>
                <Card>
                    <CardHeader title="Communities"/>
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
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {communities.items.map((community) => <CommunitiesRow key={community.name}
                                                                                    community={community}/>)}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </CardContent>
                </Card>
            </Grid>
        </Grid>
    );
};

export default Communities;