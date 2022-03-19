import React from "react";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Alert from "./components/alert/Alert";
import Home from "./components/home/Home";
import LandingPage from "./components/landing/LandingPage";
import UserProfile from "./components/user-profile/UserProfile";
import AuthGuard from "./guard/AuthGuard";

const AppRoutes = (props) => {

  return (
    <Router basename={process.env.REACT_APP_BASE_HREF}>
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route element={<AuthGuard/>}>
          <Route path="/home" element={<Home />} >
            <Route path="alert" element={<Alert />}/>
            <Route path="profile" element={<UserProfile />}/>
          </Route>
        </Route>
      </Routes>
    </Router>
  );
};

export default AppRoutes;
