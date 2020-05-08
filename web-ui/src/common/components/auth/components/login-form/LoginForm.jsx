import React from 'react'
import {Field, Form} from 'formik'
import {TextField} from 'material-ui-formik-components/TextField'
import {Avatar, Button, Card, CardActions, CardContent, CardHeader, Grid, Typography} from "@material-ui/core";
import LockOpenIcon from "@material-ui/icons/LockOpen";
import {Link} from "react-router-dom";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles(theme => ({
    loginActions: {
        marginTop: theme.spacing(4)
    }
}));

const LoginForm = ({values, dirty}) => {
    const classes = useStyles();

    return (
        <Form>
            <Card>
                <CardHeader
                    avatar={
                        <Avatar>
                            <LockOpenIcon color="primary"/>
                        </Avatar>
                    }
                    title={
                        <Typography variant="h4">
                            Login
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
                                type="password"
                                label="Password"
                                name="password"
                                value={values.password}
                                margin="normal"
                            />
                        </Grid>
                    </Grid>
                </CardContent>
                <CardActions className={classes.loginActions}>
                    <Grid container direction="row" spacing={1}>
                        <Grid item xs={false} md={4}/>
                        <Grid item xs={12} md={4}>
                            <Button
                                component={Link}
                                color="secondary"
                                fullWidth
                                to="/registration"
                            >
                                Sign up
                            </Button>
                        </Grid>
                        <Grid item xs={12} md={4}>
                            <Button
                                disabled={!dirty}
                                type="submit"
                                variant="contained"
                                color="primary"
                                fullWidth
                            >
                                Login
                            </Button>
                        </Grid>
                    </Grid>
                </CardActions>
            </Card>
        </Form>
    );
};

export default LoginForm;