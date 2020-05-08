import React, {useEffect} from 'react';
import {Field, Form} from 'formik'
import {TextField} from 'material-ui-formik-components/TextField'
import {Button, Card, CardActions, CardContent, CardHeader, Grid} from '@material-ui/core';
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";

const useStyles = makeStyles((theme) => ({
    mainContent: {
        marginTop: theme.spacing(10)
    },
    communitiesContent: {
        padding: 0
    },
    saveMargin: {
        marginLeft: theme.spacing(4)
    }
}));

const EditCommunity = ({values, ...props}) => {
    const {id} = props.match.params;
    const {getCommunity, clearCommunity} = props;
    const classes = useStyles();

    useEffect(() => getCommunity(id), [getCommunity, id]);
    useEffect(() => () => clearCommunity(), [clearCommunity]);

    return (
        <Grid container className={classes.mainContent} justify="center">
            <Grid item xs={11} md={8} lg={6}>
                <Form>
                    <Card>
                        <CardHeader title="Community"/>
                        <CardContent>
                            <Grid container spacing={2} direction="column">
                                <Grid item>
                                    <Field
                                        component={TextField}
                                        fullWidth
                                        value={values.name}
                                        label="Name"
                                        name="name"
                                        margin="normal"
                                    />
                                </Grid>
                            </Grid>
                        </CardContent>
                        <CardActions>
                            <Grid container justify="flex-end">
                                <Grid item>
                                    <Button
                                        component={Link}
                                        color="secondary"
                                        fullWidth
                                        to="/communities"
                                    >
                                        Cancel
                                    </Button>
                                </Grid>
                                <Grid item>
                                    <Button
                                        variant="contained"
                                        type="submit"
                                        className={classes.saveMargin}
                                        color="primary"
                                    >
                                        Save
                                    </Button>
                                </Grid>
                            </Grid>
                        </CardActions>
                    </Card>
                </Form>
            </Grid>
        </Grid>
    )
};

export default EditCommunity;