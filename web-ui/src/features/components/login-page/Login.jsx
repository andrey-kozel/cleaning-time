import React from 'react';
import GuestTemplate from "../../../common/components/guest/GuestTemplate";
import LoginForm from "../../../common/components/auth/components/login-form/LoginFormContainer";

const Login = ({history}) => {
    return (
        <GuestTemplate>
            <LoginForm history={history}/>
        </GuestTemplate>
    );
};

export default Login;