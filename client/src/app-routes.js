import React from "react";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LandingPage from "./components/landing/LandingPage";
import Login from "./components/login/Login";
import Alerts from "./components/alert/Alerts";
import Menus from "./components/side-navigation/Menus";
import UserProfile from "./components/user-profile/UserProfile";
import AuthGuard from "./guard/AuthGuard";
import SearchProduct from "./components/searchProducts/SearchProduct";
import Register from "./components/register";
import SetSecurityQuestion from "./components/forgetPassword/SetSecurityQuestion";
import ResetPassword from "./components/forgetPassword/ResetPassword";
import NewPassword from "./components/forgetPassword/NewPassword";
import Addproduct from "./components/products/addproduct";
import Feedback from "./components/feedback/Feedback";
import Analytics from "./components/analytics/Analytics";
import UserManagement from "./components/user_management/UserManagement";
import AddStore from "./components/store/AddStore";
import AddBrand from "./components/brand/AddBrand";
import AddAdminproduct from "./components/products/addAdminproduct";
import AnalyticsAdmin from "./components/analytics/AnalyticsAdmin";
import ComparePrice from "./components/ComparePrices/ComparePrice";


const AppRoutes = (props) => {
  return (
    <Router basename={process.env.REACT_APP_BASE_HREF}>
      <Routes>
        <Route path="/" element={<LandingPage />}>
          <Route index element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route
            path="/setSecurityQuestion"
            element={<SetSecurityQuestion />}
          />
          <Route path="/resetPassword" element={<ResetPassword />} />
          <Route path="/newPassword" element={<NewPassword />} />
        </Route>
        <Route element={<AuthGuard />}>
          <Route path="/home" element={<Menus />}>
            <Route index element={<SearchProduct />} />
            <Route path="compare" element={<ComparePrice { ...props } />} />
            <Route path="analytics" element={<Analytics />} />
            <Route path="alert" element={<Alerts />} />
            <Route path="profile" element={<UserProfile />} />
            <Route path="addproduct" element={<Addproduct />} />
            <Route path="feedback" element={<Feedback />} />
            <Route path="addstore" element={<AddStore />} />
            <Route path="addbrand" element={<AddBrand />} />
            <Route path="addproductadmin" element={<AddAdminproduct />} />
            <Route path="users" element={<UserManagement />} />
            <Route path="analyticsadmin" element={<AnalyticsAdmin />} />
            <Route path="addstore" element={<AddStore />} />
          </Route>
        </Route>
      </Routes>
    </Router>
  );
};

export default AppRoutes;
