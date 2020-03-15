import React from 'react';
import {
    Avatar,
    Button,
    Card,
    CardActions,
    CardContent,
    CardHeader,
    Grid,
    TextField,
    Typography
} from '@material-ui/core';
import {Link} from 'react-router-dom';
import LockOpenIcon from '@material-ui/icons/LockOpen';
import {makeStyles} from "@material-ui/core/styles";
import GuestTemplate from "../guest/GuestTemplate";

const useStyles = makeStyles(theme => ({
    loginActions: {
        marginTop: theme.spacing(4)
    }
}));

const Login = (props) => {
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

    return (
        <GuestTemplate>
            <form onSubmit={handleSubmit}>
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
                                <TextField
                                    fullWidth
                                    error={errors.email && touched.email}
                                    label="Email"
                                    name="email"
                                    value={values.email}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    helperText={(errors.email && touched.email) && errors.email}
                                    margin="normal"
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    fullWidth
                                    type="password"
                                    error={errors.password && touched.password}
                                    label="Password"
                                    name="password"
                                    value={values.password}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    helperText={(errors.password && touched.password) && errors.password}
                                    margin="normal"
                                />
                            </Grid>
                        </Grid>
                    </CardContent>
                    <CardActions className={classes.loginActions}>
                        <Grid container direction="row" spacing={1}>
                            <Grid item xs={6}/>
                            <Grid item xs={3}>
                                <Button
                                    component={Link}
                                    color="secondary"
                                    fullWidth
                                    to="/registration"
                                >
                                    Sign up
                                </Button>
                            </Grid>
                            <Grid item xs={3}>
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
            </form>
        </GuestTemplate>
    );
};

export default Login;