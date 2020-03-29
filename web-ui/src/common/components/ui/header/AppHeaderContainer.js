import {connect} from 'react-redux';
import AppHeader from "./AppHeader";


const mapStateToProps = (state) => {
    return {
        accessToken: state.login.accessToken
    }
};

export default connect(mapStateToProps)(AppHeader);