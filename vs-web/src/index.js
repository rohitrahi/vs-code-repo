import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';

localStorage.setItem("baseURL", "http://144.21.62.193");
ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

