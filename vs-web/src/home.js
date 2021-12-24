import "./App.css";
import { Navigate } from "react-router";

const Home = () => {
  const token = sessionStorage.getItem("token");
  if (token == null) {
    return <Navigate to='../signin' />
  }
  
  return (
    <div>
      <br />
      <br />
      <center>
        <h1>Welcome to Vision Stays {sessionStorage.getItem('emailId')}!! </h1>
      </center>
    </div>
  );
};

export default Home;
