import React from "react";
import './App.css';
import { Provider } from "react-redux";
import { MuiThemeProvider } from "@material-ui/core";
import { theme } from "./themes/theme";
import { ToastContainer } from 'react-toastify';

import 'react-toastify/dist/ReactToastify.css';
import store from "./store";
import AppRoutes from "./app-routes";





function App() {


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
        <AppRoutes />
      </MuiThemeProvider>
    </Provider>
  );
}

export default App;
