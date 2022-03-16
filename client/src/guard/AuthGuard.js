import React from 'react';
import { useSelector } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";

const AuthGuard = ({ component: Component, auth, ...rest }) => {
    const authentication = useSelector((state) => state.authentication)
    return authentication.token == null ? <Navigate to="/" /> : <Outlet />;
} 

export default AuthGuard;