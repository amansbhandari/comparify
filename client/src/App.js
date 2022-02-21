import './App.css';
import { Provider } from "react-redux";
import { MuiThemeProvider } from "@material-ui/core";
import { theme } from "./themes/theme";

import store from "./store";
import AppRoutes from "./app-routes";

function App() {
  return (
    <Provider store={store}>
      <MuiThemeProvider theme={theme}>
          <AppRoutes />
      </MuiThemeProvider>
    </Provider>
  );
}

export default App;
