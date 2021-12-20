import React from "react";

import "./App.css";
import { Navigate } from "react-router";

const SignoutForm = () => {
  sessionStorage.clear();
  return <Navigate to='../signin' />
};

export default SignoutForm;
