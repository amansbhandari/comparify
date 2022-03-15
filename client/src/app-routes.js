import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LandingPage from "./components/landing/LandingPage";
import Menus from "./components/side-navigation/Menus";

const AppRoutes = (props) => {

  return (
    <Router basename={process.env.REACT_APP_BASE_HREF}>
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/home" element={<Menus/>} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
