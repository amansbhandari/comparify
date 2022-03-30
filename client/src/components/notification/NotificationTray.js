import { Box, Divider, Drawer, IconButton, List, Typography } from "@mui/material";
import { styled, useTheme } from '@mui/material/styles';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import React, { forwardRef, useImperativeHandle } from "react";

import useStyles from "../../hooks/use-styles";

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

    const classes = useStyles(style);
    const [open, setOpen] = React.useState(false);

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

                </List>
            </Drawer>
        </Box>

    )

})

export default NotificationTray;