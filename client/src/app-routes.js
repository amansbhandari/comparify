import React from "react";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LandingPage from "./components/landing/LandingPage";
import Login from "./components/login/Login";
import Alerts from "./components/alert/Alerts";
import Menus from "./components/side-navigation/Menus";
import UserProfile from "./components/user-profile/UserProfile";
import AuthGuard from "./guard/AuthGuard";
import Register from "./components/register";
import SetSecurityQuestion from "./components/forgetPassword/SetSecurityQuestion";
import ResetPassword from "./components/forgetPassword/ResetPassword";
import NewPassword from "./components/forgetPassword/NewPassword";
import Addproduct from "./components/products/addproduct";
import Feedback from "./components/feedback/Feedback";
import AddStore from "./components/store/AddStore";


const AppRoutes = (props) => {

  return (
    <Router basename={process.env.REACT_APP_BASE_HREF}>
      <Routes>
        <Route path="/" element={<LandingPage />} >
          <Route index element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/setSecurityQuestion" element={<SetSecurityQuestion />} />
          <Route path="/resetPassword" element={<ResetPassword />} />
          <Route path="/newPassword" element={<NewPassword />} />
        </Route>
        <Route element={<AuthGuard />}>
          <Route path="/home" element={<Menus />} >
            <Route path="alert" element={<Alerts />} />
            <Route path="profile" element={<UserProfile />} />
            <Route path="addproduct" element={<Addproduct />} />
            <Route path="feedback" element={<Feedback />} />
            <Route path="addstore" element={<AddStore/>}/>
          </Route>
        </Route>
      </Routes>
    </Router>
  );

};

export default AppRoutes;