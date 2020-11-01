import * as Yup from "yup";
import {performLogin} from "../../components/auth/ducks";
import {compose} from "redux";
import {connect} from "react-redux";
import {withFormik} from "formik";
import Login from "./Login";

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

const mapDispatchToProps = {
    performLogin
};

export default compose(
    connect(null, mapDispatchToProps),
    withFormik(loginFormSettings),
)(Login);