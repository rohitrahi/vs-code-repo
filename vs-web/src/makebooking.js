import axios from "axios";
import { useFormik } from "formik";
import React, { useState, useEffect } from "react";
import { Col, Container, Form, Row, Button, Dropdown } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";

const MakeBooking = () => {
  const { id } = useParams();
  const [roomTypes, setRoomTypes] = useState([]);
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const corsConfig = { headers: { "Access-Control-Allow-Origin": "*" } };
  const [bookingData, setBookingData] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    let baseURL = localStorage.getItem("baseURL");
    axios.get(baseURL + "/vs-hotels/api/hotels/" + id, corsConfig).then(
      (res) => {
        const hotel = res.data;
        console.log(hotel);
        setIsLoaded(true);
        document.getElementById("hotelName").value = hotel.hotelName;

        bookingData.customerId = sessionStorage.getItem("emailId");
        bookingData.bookingAmount = 0;
        bookingData.bookingDate = Date.now();
        bookingData.hotelId = id;
        bookingData.hotelName = hotel.hotelName;
        bookingData.checkInDate = Date();
        bookingData.checkOutDate = Date() + 1;
        bookingData.numberOfNights = 1;
        bookingData.paymentCompleted = true;

        setBookingData(bookingData);
      },
      (error) => {
        setIsLoaded(true);
        setError(error);
      }
    );

    axios.get(baseURL + "/vs-hotels/api/roomtypes", corsConfig).then(
      (res) => {
        const roomTypes = res.data.roomTypes;
        console.log(roomTypes);
        setIsLoaded(true);
        setRoomTypes(roomTypes);
        bookingData.roomTypeId = roomTypes[0].roomTypeId;
        bookingData.roomTypeName = roomTypes[0].roomTypeName;
        bookingData.description = roomTypes[0].description;
        bookingData.pricePerNight = roomTypes[0].pricePerNight;
        document.getElementById("description").value = bookingData.description;
        document.getElementById("pricePerNight").value =
          bookingData.pricePerNight;
        document.getElementById("checkInDate").value = bookingData.checkOutDate;
        document.getElementById("checkInDate").value = bookingData.checkOutDate;
        console.log(bookingData);
      },
      (error) => {
        setIsLoaded(true);
        setError(error);
      }
    );
  }, []);

  function onNumNightsChange(e) {
    bookingData.numberOfNights = parseFloat(e.target.value);
    bookingData.pricePerNight = parseFloat(document.getElementById("pricePerNight").value);

    bookingData.bookingAmount =
      bookingData.numberOfNights * bookingData.pricePerNight;
    document.getElementById("bookingAmount").value = bookingData.bookingAmount;
  }

  function onRoomTypeChange(e) {
    var roomTypeId = e.target.value;
    var roomType = roomTypes.filter((a) => a.roomTypeId == roomTypeId)[0];
    bookingData.roomTypeId = roomType.roomTypeId;
    bookingData.roomTypeName = roomType.roomTypeName;
    bookingData.description = roomType.description;
    bookingData.pricePerNight = roomType.pricePerNight;

    document.getElementById("description").value = roomType.description;
    document.getElementById("pricePerNight").value = roomType.pricePerNight;
    bookingData.numberOfNights = parseFloat(
      document.getElementById("numberOfNights").value
    );
    bookingData.bookingAmount =
      bookingData.numberOfNights * parseFloat(roomType.pricePerNight);
    document.getElementById("bookingAmount").value = bookingData.bookingAmount;
  }

  const formik = useFormik({
    initialValues: {
      pricePerNight: 0,
      numberOfNights: 0,
      paymentCompleted: true,
    },
    onSubmit: (formData) => {
      bookingData.checkInDate = document.getElementById("checkInDate").value;
      bookingData.checkOutDate = document.getElementById("checkOutDate").value;
      console.log(bookingData);
      var postData = {};


      postData.bookingAmount = bookingData.bookingAmount;
      postData.bookingDate = bookingData.bookingDate;
      postData.checkInDate = bookingData.checkInDate;
      postData.checkOutDate = bookingData.checkOutDate;
      postData.customerId = bookingData.customerId;
      postData.description = bookingData.description;
      postData.hotelId = bookingData.hotelId;
      postData.hotelName = bookingData.hotelName;
      postData.numberOfNights = bookingData.numberOfNights;
      postData.paymentCompleted = bookingData.paymentCompleted;
      postData.pricePerNight = bookingData.pricePerNight;
      postData.roomTypeId = bookingData.roomTypeId;
      postData.roomTypeName = bookingData.roomTypeName;

      console.log(postData);

      let baseURL = localStorage.getItem("baseURL");
      axios
        .post(baseURL+"/vs-bookings/api/bookings", postData, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          navigate("../mybookings/");
        })
        .catch((err) => {
          var res = err.response.data.message;
        });
    },
  });

  return (
    <div>
      <br />
      <Container>
        <h3 className="test-success mt-5 p-3 text-center rounded">
          Make New Booking
        </h3>

        <Form onSubmit={formik.handleSubmit}>
          <Row>
            <Col lg={15} md={6} sm={12} className="p-5  m-auto shadow-sm ">
              <table className="bookingform">
                <tr>
                  <td>
                    <label>Hotel Name</label>
                  </td>
                  <td>
                    <input
                      type="text"
                      name="hotelName"
                      id="hotelName"
                      disabled
                      value={bookingData.hotelName}
                    />
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>Room Type</label>
                  </td>
                  <td>
                    <select
                      onChange={onRoomTypeChange}
                      className="mb-3 lg={15}"
                      name="roomType"
                      style={{ display: "block", align: "center" }}
                    >
                      {roomTypes.map((roomType) => (
                        <option
                          id={roomType.roomTypeId}
                          value={roomType.roomTypeId}
                          label={roomType.roomTypeName}
                        />
                      ))}
                    </select>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>Description</label>
                  </td>
                  <td>
                    <textarea
                      name="description"
                      id="description"
                      readOnly
                    ></textarea>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>Price Per Night</label>
                  </td>
                  <td>
                    <input
                      type="text"
                      name="pricePerNight"
                      id="pricePerNight"
                      value={bookingData.pricePerNight}
                      readOnly
                    />
                  </td>
                </tr>
                <tr>
                  <td>Check in Date</td>
                  <td>
                    <Form.Control
                      type="date"
                      id="checkInDate"
                      name="checkInDate"
                      placeholder="Check in Date"
                    />
                  </td>
                </tr>
                <tr>
                  <td>Check out Date</td>
                  <td>
                    <Form.Control
                      type="date"
                      id="checkOutDate"
                      name="checkOutDate"
                      placeholder="Check out Date"
                    />
                  </td>
                </tr>
                <tr>
                  <td>Number Of Nights</td>
                  <td>
                    <select
                      className="mb-3 lg={15}"
                      name="numberOfNights"
                      id="numberOfNights"
                      style={{ display: "block", align: "center" }}
                      onChange={onNumNightsChange}
                    >
                      <option value="1" label="1" />
                      <option value="2" label="2" />
                      <option value="3" label="3" />
                      <option value="4" label="4" />
                      <option value="5" label="5" />
                    </select>
                  </td>
                </tr>
                <tr>
                  <td>Booking Amount</td>
                  <td>
                    <input
                      type="text"
                      name="bookingAmount"
                      id="bookingAmount"
                      readOnly
                    />
                  </td>
                </tr>
                <tr>
                  <td colSpan="2">* prices are in USD</td>
                </tr>
              </table>
              <Button variant="success btn-block" type="submit">
                Book Room
              </Button>
            </Col>
          </Row>
        </Form>
      </Container>
    </div>
  );
};

export default MakeBooking;
