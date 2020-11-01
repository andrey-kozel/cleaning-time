import React from 'react';
import {Field, Form} from 'formik'
import {TextField} from 'material-ui-formik-components/TextField'
import {
    Avatar,
    Button,
    Card,
    CardActions,
    CardContent,
    CardHeader,
    Grid,
    Typography
} from '@material-ui/core';
import {Link} from 'react-router-dom';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import {makeStyles} from "@material-ui/core/styles";
import GuestTemplate from "../../components/guest-template/GuestTemplate";

const useStyles = makeStyles(theme => ({
    registrationActions: {
        marginTop: theme.spacing(4)
    }
}));

const Registration = ({values, dirty}) => {
    const classes = useStyles();

    return (
        <GuestTemplate>
            <Form>
                <Card>
                    <CardHeader
                        avatar={
                            <Avatar>
                                <ExitToAppIcon color="primary"/>
                            </Avatar>
                        }
                        title={
                            <Typography variant="h4">
                                Registration
                            </Typography>
                        }
                    />
                    <CardContent>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <Field
                                    component={TextField}
                                    fullWidth
                                    label="Email"
                                    name="email"
                                    value={values.email}
                                    margin="normal"
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Field
                                    component={TextField}
                                    fullWidth
                                    type="password"
                                    label="Password"
                                    name="password"
                                    value={values.password}
                                    margin="normal"
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Field
                                    component={TextField}
                                    fullWidth
                                    type="password"
                                    label="Password confirmation"
                                    name="passwordConfirmation"
                                    value={values.passwordConfirmation}
                                    margin="normal"
                                />
                            </Grid>
                        </Grid>
                    </CardContent>
                    <CardActions className={classes.registrationActions}>
                        <Grid container direction="row" spacing={1}>
                            <Grid item xs={false} sm={4}/>
                            <Grid item xs={12} sm={4}>
                                <Button
                                    component={Link}
                                    color="secondary"
                                    fullWidth
                                    to="/login"
                                >
                                    Login
                                </Button>
                            </Grid>
                            <Grid item xs={12} sm={4}>
                                <Button
                                    disabled={!dirty}
                                    type="submit"
                                    variant="contained"
                                    color="primary"
                                    fullWidth
                                >
                                    Register
                                </Button>
                            </Grid>
                        </Grid>
                    </CardActions>
                </Card>
            </Form>
        </GuestTemplate>
    );
};

export default Registration;