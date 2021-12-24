import React from "react";
import axios from "axios";

export default class BookingList extends React.Component {
  state = {
    hotels: [],
  };

  corsConfig = { headers: { "Access-Control-Allow-Origin": "*" } };

  componentDidMount() {
    let baseURL = localStorage.getItem("baseURL");
    axios
      .get(baseURL+"/vs-hotels/api/hotels", this.corsConfig)
      .then((res) => {
        const hotels = res.data.hotels;
        console.log(hotels);
        this.setState({ hotels });
      });
  }

  render() {
    return (
      <>
        <div>Hotels</div>
        <ul>
          {this.state.hotels.map((hotel) => (
            <li key={hotel.hotelId}>{hotel.hotelId}</li>
          ))}
        </ul>
      </>
    );
  }
}
