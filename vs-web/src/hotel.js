import React, { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Hotel = () => {

  const navigate = useNavigate();
  
  const token = sessionStorage.getItem("token");
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [hotels, setHotels] = useState([]);
  const corsConfig = { headers: { "Access-Control-Allow-Origin": "*" } };


  useEffect(() => {
    if (token == null) {
      navigate("../signin");
    }
  
      let baseURL = localStorage.getItem("baseURL");
    axios.get(baseURL+"/vs-hotels/api/hotels", corsConfig).then(
      (res) => {
        const hotels = res.data.hotels;
        console.log(hotels);
        setIsLoaded(true);
        setHotels(hotels);
      },
      (error) => {
        setIsLoaded(true);
        setError(error);
      },
    );
  }, []);

  const makeBooking = (id) => {
    navigate("../makebooking/"+id);
  }

  if (error) {
    return <div className="bg_image">Error : {error.message}</div>;
  } else if (!isLoaded) {
    return <div className="bg_image">Loading....</div>;
  } else {
    return (
      <div className="bg_image"> 
      <center>
        <h2>Hotels Available</h2>
        <table className="vstable">
          <thead>
            <tr>
              <th>#</th>
              <th>Hotel Name</th>
              <th>Address</th>
              <th>City</th>
              <th>Book Now</th>
            </tr>
          </thead>
          <tbody>
            {hotels.map((hotel) => (
              <tr key={hotel.hotelId}>
                <td>{hotel.hotelId}</td>
                <td>{hotel.hotelName}</td>
                <td>{hotel.address}</td>
                <td>{hotel.cityName}</td>
                <td>
                  <Button variant="primary" onClick={() => makeBooking(hotel.hotelId)}>Book</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </center>
      </div>
    );
  }
};
export default Hotel;
