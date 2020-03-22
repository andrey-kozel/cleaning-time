import {createMuiTheme} from "@material-ui/core/styles";
import { blueGrey, cyan } from '@material-ui/core/colors';

const theme = createMuiTheme({
    overrides: {},
    palette: {
        primary: {
            main: blueGrey[800]
        },
        secondary: {
            main: cyan[200]
        }
    }
});

export default theme;