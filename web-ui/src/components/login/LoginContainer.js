import {connect} from 'react-redux';
import {withFormik,} from 'formik';
import * as Yup from 'yup'
import {compose} from "redux";

import Login from "./Login";
import {loginUser, loinUserFailed} from "../../actions/actions";
import {withCleaningTimeService} from "../hoc";

const validationSchema = Yup.object().shape({
    email: Yup.string()
        .email('Invalid email address')
        .required('This field is required'),
    password: Yup.string()
        .required('This field is required')
        .min(8, 'Password length must be greater than 7 symbols')
});

const loginFormSettings = {
    mapPropsToValues: () => ({
        email: '',
        password: ''
    }),

    handleSubmit: (values, {props}) => {
        props.cleaningTimeService.loginUser(values)
            .then(response => props.loginUser(response.data))
            .catch(error => props.loinUserFailed(error.response));
    },

    validationSchema: validationSchema,
    validateOnBlur: true,
    validateOnChange: false,
    displayName: 'LoginForm',
};

const mapDispatchToProps = {
    loginUser,
    loinUserFailed
};

export default compose(
    connect(null, mapDispatchToProps),
    withCleaningTimeService(),
    withFormik(loginFormSettings),
)(Login);