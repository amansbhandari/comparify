import React, { useRef } from "react";
import './App.css';
import { Provider } from "react-redux";
import { MuiThemeProvider } from "@material-ui/core";
import { theme } from "./themes/theme";
import { ToastContainer } from 'react-toastify';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';

import 'react-toastify/dist/ReactToastify.css';
import useStyles from "./hooks/use-styles";
import store from "./store";
import AppRoutes from "./app-routes";
import NotificationTray from "./components/notification/NotificationTray";


const style = {
  root: {},
  fabContainer: {
    "position": "absolute !important",
    "bottom": "50px",
    "right": "50px"
  }
};

function App() {

  const classes = useStyles(style);

  const notificationRef = useRef();

  return (
    <Provider store={store}>
      <MuiThemeProvider theme={theme}>
        <ToastContainer
          position="top-right"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
        />
        <NotificationTray ref={notificationRef}></NotificationTray>
        <Fab color="primary" aria-label="add" className={classes.fabContainer}>
          <AddIcon onClick={() => notificationRef.current.openNotificationTray()} />
        </Fab>
        <AppRoutes />

      </MuiThemeProvider>
    </Provider>
  );
}

export default App;
