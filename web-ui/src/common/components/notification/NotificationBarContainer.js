import {connect} from "react-redux";
import NotificationBar from "./NotificationBar";
import {hideNotification} from './ducks';

const mapStateToProps = (state) => {
    return {
        ...state.notification
    }
};

const mapDispatchToProps = {
    hideNotification
};

export default connect(mapStateToProps, mapDispatchToProps)(NotificationBar);