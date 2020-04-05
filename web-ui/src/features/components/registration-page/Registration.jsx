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
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import {makeStyles} from "@material-ui/core/styles";
import GuestTemplate from "../../../common/components/ui/guest/GuestTemplate";

const useStyles = makeStyles(theme => ({
    registrationActions: {
        marginTop: theme.spacing(4)
    }
}));

const Registration = (props) => {
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
                            <Grid item xs={12}>
                                <TextField
                                    fullWidth
                                    type="password"
                                    error={errors.passwordConfirmation && touched.passwordConfirmation}
                                    label="Password confirmation"
                                    name="passwordConfirmation"
                                    value={values.passwordConfirmation}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    helperText={(errors.passwordConfirmation && touched.passwordConfirmation) && errors.passwordConfirmation}
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
            </form>
        </GuestTemplate>
    );
};

export default Registration;