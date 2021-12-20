import React, { useEffect } from "react";
import { BrowserRouter as Router, Route, Link, Routes  } from "react-router-dom";
import { Navbar, Nav, Container } from "react-bootstrap";

import Contact from "./contact";
import Home from "./home";
import Hotel from "./hotel";
import MyBookings from "./mybookings";
import MyProfile from "./myprofile";
import SigninForm from "./signin";
import SignupForm from "./signup";
import SignoutForm from "./signout";
import MakeBooking from "./makebooking";

const App = () => {
  return (
    <Router>
      <div className="App">
        <div class="body">
          <div class="banner">
            <Navbar bg="light" variant="dark">
              <Container className="topnav">
                <Nav active="/">
                  <Nav.Link as={Link} to={"/"}>
                    Home
                  </Nav.Link>
                  <Nav.Link as={Link} to={"/contact"}>
                    Contact Us
                  </Nav.Link>
                  <Nav.Link as={Link} to={"/hotel"}>
                    Hotel
                  </Nav.Link>
                  <Nav.Link as={Link} to={"/mybookings"}>
                    My Bookings
                  </Nav.Link>
                  <Nav.Link as={Link} to={"/signout"}>
                    Sign Out
                  </Nav.Link>
                </Nav>
              </Container>
            </Navbar>
            <br />
          </div>
        </div>

        <Routes>
          <Route
            path="/"
            element={
              <div className="bg_image">
                {" "}
                <Home />{" "}
              </div>
            }
          />
          <Route
            path="/hotel"
            element={
              <div className="bg_image">
                {" "}
                <Hotel />{" "}
              </div>
            }
          />
          <Route
            path="/mybookings"
            element={
              <div className="bg_image">
                {" "}
                <MyBookings />{" "}
              </div>
            }
          />
          <Route
            path="/myprofile"
            element={
              <div className="bg_image">
                {" "}
                <MyProfile />{" "}
              </div>
            }
          />
          <Route
            path="/contact"
            element={
              <div className="bg_image">
                {" "}
                <Contact />{" "}
              </div>
            }
          />
          <Route
            path="/makebooking/:id"
            element={
              <div className="bg_image">
                {" "}
                <MakeBooking />{" "}
              </div>
            }
          />

          <Route
            path="/signin"
            element={
              <div className="bg_image">
                {" "}
                <SigninForm />{" "}
              </div>
            }
          />
          <Route
            path="/signup"
            element={
              <div className="bg_image">
                {" "}
                <SignupForm />{" "}
              </div>
            }
          />
          <Route
            path="/signout"
            element={
              <div className="bg_image">
                {" "}
                <SignoutForm />{" "}
              </div>
            }
          />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
