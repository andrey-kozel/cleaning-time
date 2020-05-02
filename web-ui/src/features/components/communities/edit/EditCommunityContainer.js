import {connect} from 'react-redux';
import {compose} from "redux";
import {getCommunity, saveCommunity, updateCommunity} from "./ducks";
import EditCommunity from './EditCommunity'
import * as Yup from "yup";
import {withFormik} from "formik";

const validationSchema = Yup.object().shape({
    name: Yup.string()
        .required('This field is required')
});

const communitySettings = {
    mapPropsToValues: (props) => ({
        name: props.name
    }),

    handleSubmit: (values, {props}) => {
        const {id} = props.match.params;

        if (id) {
            props.updateCommunity(id, values);
        } else {
            props.saveCommunity(values, props.history);
        }

    },
    enableReinitialize: true,
    validationSchema: validationSchema,
    validateOnBlur: true,
    validateOnChange: false,
    displayName: 'CommunityForm',
};

const mapStateToProps = (state) => {
    return {
        name: state.community.community.name
    }
};

const mapDispatchToProps = {
    getCommunity,
    saveCommunity,
    updateCommunity
};

export default compose(
    connect(mapStateToProps, mapDispatchToProps),
    withFormik(communitySettings),
)(EditCommunity);