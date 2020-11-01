import React from "react";
import {Field, Form, Formik} from "formik";
import {TextField} from "material-ui-formik-components/TextField";
import {Button, Card, CardActions, CardContent, CardHeader, Grid} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";
import AuthorizedTemplate from "../../../components/authorized-template/AuthorizedTemplate";
import * as Yup from "yup";

const useStyles = makeStyles((theme) => ({
    saveMargin: {
        marginLeft: theme.spacing(4)
    }
}));

const CommunityForm = ({community, saveCommunity}) => {
    const classes = useStyles();

    const initialValues = {
        name: community.name
    };

    const validationSchema = Yup.object().shape({
        name: Yup.string()
            .required("This field is required")
    });

    return (
        <AuthorizedTemplate>
            <Grid item xs={11} md={8} lg={6}>
                <Formik initialValues={initialValues}
                        validationSchema={validationSchema}
                        enableReinitialize
                        validateOnBlur
                        validateOnChange={false}
                        onSubmit={saveCommunity}>
                    {formik =>
                        <Form onSubmit={formik.handleSubmit}>
                            <Card>
                                <CardHeader title="Community"/>
                                <CardContent>
                                    <Grid container spacing={2} direction="column">
                                        <Grid item>
                                            <Field
                                                component={TextField}
                                                fullWidth
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
                    }
                </Formik>
            </Grid>
        </AuthorizedTemplate>
    );
};

export default CommunityForm;