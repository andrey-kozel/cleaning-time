import {connect} from 'react-redux';
import {withFormik,} from 'formik';
import * as Yup from 'yup'
import {compose} from "redux";

import Registration from "./Registration";
import {performRegistration} from "../../actions";
import {withCleaningTimeService} from "../hoc";

const validationSchema = Yup.object().shape({
    email: Yup.string()
        .email('Invalid email address')
        .required('This field is required'),
    password: Yup.string()
        .required('This field is required')
        .min(8, 'Password length must be greater than 7 symbols'),
    passwordConfirmation: Yup.string()
        .required('This field is required')
        .min(8, 'Confirmation length must be greater than 7 symbols')
        .oneOf([Yup.ref("password"), null], "Password must match")
});

const loginFormSettings = {
    mapPropsToValues: () => ({
        email: '',
        password: '',
        passwordConfirmation: ''
    }),

    handleSubmit: (values, {props}) => {
        props.cleaningTimeService.registerUser(values)
            .then(response => props.registerUser(response.data))
            .catch(error => props.registerUserFailed(error.response));
    },

    validationSchema: validationSchema,
    validateOnBlur: true,
    validateOnChange: false,
    displayName: 'RegistrationForm',
};

const mapDispatchToProps = (dispatch, {cleaningTimeService}) => {
    return {
        performRegistration: performRegistration(cleaningTimeService, dispatch)
    }
};

export default compose(
    connect(null, mapDispatchToProps),
    withCleaningTimeService(),
    withFormik(loginFormSettings),
)(Registration);