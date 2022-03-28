import React from "react";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Alerts from "./components/alert/Alerts";
import Menus from "./components/side-navigation/Menus";
import LandingPage from "./components/landing/LandingPage";
import UserProfile from "./components/user-profile/UserProfile";
import AuthGuard from "./guard/AuthGuard";
import ForgotPassword from "./components/forgetPassword/ForgetPassword";


const AppRoutes = (props) => {

  return (
    <Router basename={process.env.REACT_APP_BASE_HREF}>
     
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route element={<AuthGuard />}>
          <Route path="/home" element={<Menus />}>
            <Route path="alert" element={<Alerts />} />
            <Route path="profile" element={<UserProfile />} />
          </Route>
        </Route>
        <Route path="/forgetpassword" element={<ForgotPassword />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;