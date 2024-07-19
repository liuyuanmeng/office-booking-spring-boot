import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SpaceListPage from './components/SpaceList';
import BookingFormPage from './components/BookingForm'
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <Router>
      <div className="App">
        <h1>Office Space Booking</h1>
        <Routes>
          <Route path="/" element={<SpaceListPage />} />
          <Route path="/book" element={<BookingFormPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
