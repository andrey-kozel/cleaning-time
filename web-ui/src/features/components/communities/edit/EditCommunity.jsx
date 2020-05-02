import React, {useEffect} from 'react';
import {Button, Card, CardActions, CardContent, CardHeader, Grid, TextField} from '@material-ui/core';
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

const EditCommunity = (props) => {
    const {id} = props.match.params;
    const {getCommunity} = props;
    const classes = useStyles();
    const {
        values,
        touched,
        errors,
        dirty,
        handleChange,
        handleBlur,
        handleSubmit,
    } = props;

    useEffect(() => {
        if (id) {
            getCommunity(id)
        }
    }, [id])

    return (
        <Grid container className={classes.mainContent} justify="center">
            <Grid item xs={11} md={8} lg={6}>
                <form onSubmit={handleSubmit}>
                    <Card>
                        <CardHeader title="Community"/>
                        <CardContent>
                            <Grid container spacing={2} direction="column">
                                <Grid item>
                                    <TextField
                                        fullWidth
                                        error={errors.name && touched.name}
                                        label="Name"
                                        name="name"
                                        value={values.name}
                                        onChange={handleChange}
                                        onBlur={handleBlur}
                                        helperText={(errors.name && touched.name) && errors.name}
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
                </form>
            </Grid>
        </Grid>
    )
};

export default EditCommunity;