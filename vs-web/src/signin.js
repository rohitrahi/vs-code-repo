import axios from "axios";
import { useFormik } from "formik";
import React from "react";
import { Col, Container, Form, Row, Button } from "react-bootstrap";
import { Navigate , useNavigate} from "react-router-dom";

const SigninForm = () => {
  let navigate = useNavigate();

  const formik = useFormik({
    initialValues: {
      id: "",
      password: "",
    },
    onSubmit: (formData) => {
      let baseURL = localStorage.getItem("baseURL");
      axios
        .post(baseURL+"/vs-customers/api/signin", formData, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          sessionStorage.setItem("token", res);
          sessionStorage.setItem("emailId", formData.id);
          navigate("../", { replace: true });
        })
        .catch((err) => {
          var res = err.response.data.message;
        });
    },
  });

  async function signUp() {
    navigate("../signup", { replace: true });
  }


  return (
    <div>
        <br />
        <Container>
          <h3 className="test-success mt-5 p-3 text-center rounded">
            Customer Login
          </h3>
          <Row>
            <Col lg={5} md={6} sm={12} className="p-5  m-auto shadow-sm ">
              <Form onSubmit={formik.handleSubmit}>
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
                  Sign in
                </Button>
                <br />
                <br />
                <br />
                <div class="d-flex align-items-center justify-content-center pb-4">
                  <p class="mb-0 me-2">Don't have an account?</p>
                  <Button type="button" class="btn btn-block" onClick={signUp}>
                    Sign up
                  </Button>
                </div>
              </Form>
            </Col>
          </Row>
        </Container>
    </div>
  );
};

export default SigninForm;
