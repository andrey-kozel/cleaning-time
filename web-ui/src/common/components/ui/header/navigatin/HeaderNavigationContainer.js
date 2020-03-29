import {connect} from "react-redux"

import {switchPage} from "./ducks"
import HeaderNavigation from "./HeaderNavigation";

const mapStateToProps = (state) => {
    return {
        path: state.navigation.path
    }
};

const mapDispatchToProps = {
    switchPage
};

export default connect(mapStateToProps, mapDispatchToProps)(HeaderNavigation);