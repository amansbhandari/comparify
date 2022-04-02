import { Box, Button, Divider, Drawer, IconButton, List, Typography } from "@mui/material";
import { styled, useTheme } from '@mui/material/styles';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import React from "react";

import useStyles from "../../hooks/use-styles";
import CreateAlert from "./CreateAlert";
import ViewAlerts from "./ViewAlerts";

const style = {
    root: {
        "display": "flex",
        "justify-content": "center" 
    },
    titleContainer: {
        "display": "flex",
        "justify-content": "center",
        margin: "15px"
    },
    buttonContainer: {
        margin: "15px 0px 0px 0px"
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

const Alerts = (props) => {

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
            <Box>
                <Box>
                    <Button color="primary" variant="contained" type="button" className={classes.button} 
                    onClick={handleDrawerOpen}>
                        Create
                    </Button>
                </Box>
                <Box sx={{
                    marginTop: "20px"
                }}>
                    <ViewAlerts></ViewAlerts>
                </Box>
            </Box>
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
                        <Typography variant="h5">Create Alert</Typography>
                    </Box>
                </DrawerHeader>
                <Divider />
                <List>
                    <CreateAlert></CreateAlert>
                </List>
            </Drawer>
        </Box>

    )

}

export default Alerts;