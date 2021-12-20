import axios from "axios";
import { useFormik } from "formik";
import React from "react";
import { Col, Container, Form, Row, Button } from "react-bootstrap";
import { Navigate, useNavigate } from "react-router-dom";

const SignupForm = () => {
  const [redirect, setRedirect] = React.useState(false);
  let navigate = useNavigate();


  const formik = useFormik({
    initialValues: {
      name:"",
      id: "",
      password: "",
    },
    onSubmit: (formData) => {
      let baseURL = localStorage.getItem("baseURL");
      axios
        .post(baseURL+"/vs-customers/api/signup", formData, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          navigate("../signin", {replace:true});
        })
        .catch((err) => {
          var res = err.response.data.message;
        });
    },
  });

  if(redirect) {
    return <Navigate to="/signin"/>;
  }

  return (
    <div>
        <br />
        <Container>
          <h3 className="test-success mt-5 p-3 text-center rounded">
            Customer Sign up
          </h3>
          <Row>
            <Col lg={5} md={6} sm={12} className="p-5  m-auto shadow-sm ">
              <Form onSubmit={formik.handleSubmit}>
              <Form.Group className="mb-3" controlId="name">
                  <Form.Label>Name</Form.Label>
                  <Form.Control
                    type="name"
                    name="name"
                    placeholder="name"
                    onChange={formik.handleChange}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="id">
                  <Form.Label>Email address</Form.Label>
                  <Form.Control
                    type="email"
                    name="id"
                    placeholder="Enter email"
                    onChange={formik.handleChange}
                  />
                </Form.Group>

                <Form.Group className="mb-3" controlId="password">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    name="password"
                    placeholder="Password"
                    onChange={formik.handleChange}
                  />
                </Form.Group>
                <Button variant="success btn-block" type="submit">
                  Sign up
                </Button>
              </Form>
            </Col>
          </Row>
        </Container>
    </div>
  );
};

export default SignupForm;
