import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';

localStorage.setItem("baseURL", "http://150.230.169.166");
ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

