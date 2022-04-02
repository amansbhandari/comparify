import { Box, Divider, Drawer, IconButton, List, ListItem, ListItemAvatar, ListItemText, Typography } from "@mui/material";
import { styled, useTheme } from '@mui/material/styles';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import React, { forwardRef, useEffect, useImperativeHandle } from "react";
import { useDispatch, useSelector } from "react-redux";

import AddAlertIcon from '@mui/icons-material/AddAlert';

import useStyles from "../../hooks/use-styles";
import { fetchNotifications } from "../../store/thunk/notificationThunkCreators";

//import moment from 'moment'

const style = {
    titleContainer: {
        "display": "flex",
        "justify-content": "center",
        margin: "15px"
    }
};

const drawerWidth = 400;

const DrawerHeader = styled('div')(({ theme }) => ({
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
    justifyContent: 'flex-start',
}));

const NotificationTray = forwardRef((props, ref) => {

    useImperativeHandle(ref, () => ({
        openNotificationTray() {
            handleDrawerOpen();
        }
    }))

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(fetchNotifications());
    }, [dispatch])

    const classes = useStyles(style);
    const [open, setOpen] = React.useState(false);
    const notifications = useSelector(state => state.notification.list);

    const theme = useTheme();

    const handleDrawerOpen = () => {
        setOpen(true);
    };

    const handleDrawerClose = () => {
        setOpen(false);
    };

    return (
        <Box>
            <Drawer
                sx={{
                    width: drawerWidth,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        width: drawerWidth,
                    },
                }}
                open={open} anchor="right">
                <DrawerHeader>
                    <IconButton onClick={handleDrawerClose}>
                        {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
                    </IconButton>
                    <Box className={classes.titleContainer}>
                        <Typography variant="h5">{'Notifications'}</Typography>
                    </Box>
                </DrawerHeader>
                <Divider />
                <List>

                    {notifications && notifications.map((notification, index) =>
                    (<>
                        <ListItem key={notification.id + "-list"} alignItems="flex-start">
                            <ListItemAvatar>
                                <AddAlertIcon />
                            </ListItemAvatar>
                            <ListItemText
                                primary={<><Typography
                                    sx={{ fontWeight: "bold" }}
                                    component="span"
                                    variant="body1"
                                    color="text.primary"
                                >
                                    {notification.title}
                                </Typography></>}
                                secondary={
                                    <>
                                        <Typography
                                            sx={{ "display": "block" }}
                                            component="span"
                                            variant="body1"
                                            color="text.primary"
                                        >
                                            {notification.message}
                                        </Typography>
                                        <Typography
                                            sx={{
                                                "display": "block",
                                                "marginTop": "10px",
                                                "color": "grey"
                                            }}
                                            component="span"
                                            variant="body2"
                                            color="text.primary"
                                        >
                                            {new Date(notification.audit.created_on).toLocaleString()}
                                        </Typography>

                                    </>
                                }
                            />
                        </ListItem>
                        {(index !== notifications.length - 1) && <Divider key={notification.id + "-divider"}
                         variant="inset" 
                         component="li" />
                        }
                    </>)
                    )}

                </List>
            </Drawer>
        </Box>

    )

})

export default NotificationTray;