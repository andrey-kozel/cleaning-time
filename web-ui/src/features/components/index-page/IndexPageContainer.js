import {connect} from 'react-redux'

import IndexPage from "./IndexPage";
import {switchPage} from "../../../common/components/ui/header/navigation/ducks"

const mapDispatchToProps = {
    switchPage
};

export default connect(null, mapDispatchToProps)(IndexPage);