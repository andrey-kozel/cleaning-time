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
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import {makeStyles} from "@material-ui/core/styles";

import Background from '../../assets/6ed268aa48e1f88b6d8e4a761dc83e5418cae11e-min.jpg'

const useStyles = makeStyles(theme => ({
    loginBackground: {
        backgroundImage: `linear-gradient( rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5) ), url(${Background})`,
        backgroundSize: 'cover',
        minHeight: '100vh',
        minWidth: '100%'
    },
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
        <Grid
            className={classes.loginBackground}
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justify="center"
        >
            <Grid item xs={3}>
                <form onSubmit={handleSubmit}>
                    <Card>
                        <CardHeader
                            avatar={
                                <Avatar>
                                    <ExitToAppIcon color="primary"/>
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
                                        type="submit"
                                        variant="contained"
                                        color="secondary"
                                        fullWidth
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
            </Grid>
        </Grid>
    );
};

export default Login;