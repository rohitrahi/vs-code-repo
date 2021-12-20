import React, { useState, useEffect } from "react";
import axios from "axios";

const MyProfile = () => {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [profile, setProfile] = useState([]);
  const corsConfig = { headers: { "Access-Control-Allow-Origin": "*" } };

  useEffect(() => {
    let emailId = sessionStorage.getItem("emailId")
    let baseURL = localStorage.getItem("baseURL");
    axios
    .get(baseURL+"/vs-customers/api/customers/customer/"+emailId, corsConfig)
    .then(
      (res) => {
        const profile = res.data;
        setIsLoaded(true);
        setProfile(profile);
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
      <>
      <div class="bg_image">
        <br />
        <br />
        <center>
          <h1>My Profile</h1>
        </center>
      </div>
    </>
    );
  }
};
export default MyProfile;

