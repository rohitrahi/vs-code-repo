import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';

localStorage.setItem("baseURL", "https://px4yokejywuyfmzkmjkyrkc2ee.apigateway.uk-london-1.oci.customer-oci.com/v1:");
ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

