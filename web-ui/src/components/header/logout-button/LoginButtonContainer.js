import {compose} from "redux";
import {connect} from 'react-redux';
import {withRouter} from 'react-router-dom'

import LogoutButton from "./LogoutButton";
import {performLogout} from "../../auth/ducks";

const mapDispatchToProps = {
    performLogout
};

export default compose(
    withRouter,
    connect(null, mapDispatchToProps),
)(LogoutButton);