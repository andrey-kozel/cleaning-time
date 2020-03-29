import {connect} from 'react-redux';
import {withFormik,} from 'formik';
import * as Yup from 'yup'
import {compose} from "redux";

import Login from "./Login";
import {performLogin} from "../../../common/components/auth/ducks";
import {withCleaningTimeService} from "../../../common/components/hoc";

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
        props.performLogin(values, props.history);
    },

    validationSchema: validationSchema,
    validateOnBlur: true,
    validateOnChange: false,
    displayName: 'LoginForm',
};

const mapDispatchToProps = (dispatch, {cleaningTimeService}) => {
    return {
        performLogin: performLogin(cleaningTimeService, dispatch)
    };
};

export default compose(
    withCleaningTimeService(),
    connect(null, mapDispatchToProps),
    withFormik(loginFormSettings),
)(Login);