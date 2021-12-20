import React from "react";
import { Navigate } from "react-router";

import "./App.css";

const Contact = () => {
  const token = sessionStorage.getItem("token");
  if (token == null) {
    return <Navigate to='../signin' />
  }

  return (
    <div class="bg_image">
      <br />
      <br />
      <center>
        <h1>Contact us at info@visionstays.com</h1>
      </center>
    </div>
  );
};

export default Contact;
