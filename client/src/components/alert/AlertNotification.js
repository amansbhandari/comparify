import { Box } from "@mui/system";
import React from "react";
import useStyles from "../../hooks/use-styles";

const style = {

    messageContainer: {
        "display": "block",
    },
};

const AlertNotification = (props) => {

    const classes = useStyles(style);

    return (<>
        <Box onClick={props.closeToast}>
            <span><b>{props && props.data && props.data.title}</b></span>
            <span className={classes.messageContainer}>{props && props.data && props.data.message}</span>
        </Box>
    </>)
}

export default AlertNotification