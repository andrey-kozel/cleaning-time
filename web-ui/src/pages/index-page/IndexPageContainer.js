import {connect} from 'react-redux'

import IndexPage from "./IndexPage";
import {switchPage} from "../../components/header/naigation/ducks"

const mapDispatchToProps = {
    switchPage
};

export default connect(null, mapDispatchToProps)(IndexPage);