import React, { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const MyBookings = () => {
  const navigate = useNavigate();
  const token = sessionStorage.getItem("token");
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [bookings, setBookings] = useState([]);
  const corsConfig = { headers: { "Access-Control-Allow-Origin": "*" } };

  useEffect(() => {
    if (token == null) {
      navigate("../signin");
    }
    let emailId = sessionStorage.getItem("emailId")
    let baseURL = localStorage.getItem("baseURL");
    axios
    .get(baseURL+"/vs-bookings/api/bookings/customer/"+emailId, corsConfig)
    .then(
      (res) => {
        const bookings = res.data.bookings;
        console.log(bookings);
        setIsLoaded(true);
        setBookings(bookings);
      },
      (error) => {
        setIsLoaded(true);
        setError(error);
      },
    );
  }, []);

 
  if (error) {
    return <div>Error : {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading....</div>;
  } else {
    return (
        <center>
          <h2>My Bookings</h2>

            <table className="vstable">
            <thead>
              <tr>
                <th>Booking Date</th>
                <th>Hotel Name</th>
                <th>Room Type</th>
                <th>Check in Date</th>
                <th>Check out Date</th>
              </tr>
            </thead>
            <tbody>
              {bookings.map((booking) => (
                <tr key={booking.bookingId}>
                  <td>{booking.bookingDate.slice(0, 10)}</td>
                  <td>{booking.hotelName}</td>
                  <td>{booking.roomTypeName}</td>
                  <td>{booking.checkInDate.slice(0, 10)}</td>
                  <td>{booking.checkOutDate.slice(0, 10)}</td>
                </tr>
              ))}
            </tbody>
          </table>

        </center>
    );
  }
};
export default MyBookings;

