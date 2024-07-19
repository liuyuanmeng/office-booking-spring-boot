import React, { useState, useEffect } from 'react';
// useLocation, useNavigate from react-router-dom: Hooks to access the current location and navigate between routes.
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';

const BookingFormPage = () => {
    const navigate = useNavigate();
// used const location = useLocation(); and const queryParams = new URLSearchParams(location.search); to extract the spaceId query parameter from the URL. This is how we are able to get the spaceId which is necessary for fetching the space details.
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const spaceId = queryParams.get('spaceId');
// State Variables:
    const [space, setSpace] = useState(null);  // State to store space details.
    const [username, setUsername] = useState('');  // State to store the username input.
    const [date, setDate] = useState('');  // State to store the date input.
// Checks if spaceId is present. If not, it navigates to the home page.
// If spaceId is present, it fetches the space details from the API.
// On success, it updates the space state with the fetched data.
// If there is an error, it logs the error and navigates to the home page.

    useEffect(() => {
        if (!spaceId) {
            navigate('/');  // Navigate to the home page if spaceId is not present.
        } else {
            axios.get(`http://localhost:8080/api/spaces/${spaceId}`)
                .then(response => {
                    setSpace(response.data);  // Update the state with the fetched space details.
                })
                .catch(error => {
                    console.error('Error fetching space details:', error);
                    navigate('/');  // Navigate to the home page if there's an error.
                });
        }
    }, [spaceId, navigate]);
// The array [spaceId, navigate] at the end of the useEffect hook is called the dependency array. It controls when the useEffect hook runs.
// The dependency array tells React to only run the useEffect callback function when one or more of the dependencies listed in the array change.
// In this case, the dependencies are spaceId and navigate.

    if (!space) {
        return null; // Render nothing if space details are not yet fetched
    }

    const handleSubmit = (event) => {
        // Prevents the default form submission behavior.
        event.preventDefault();
        axios.post('http://localhost:8080/api/bookings', null, {
            params: {
                username,
                date,
                spaceId: space.id  // Include spaceId in the booking request.
            }
        })
            .then(response => {
                alert('Booking created successfully!');
                navigate('/');  // Navigate to the home page on success.
            })
            .catch(error => {
                alert('Error creating booking');
                console.error('Error creating booking:', error);
            });
    };

    return (
        <div className="container">
            <h2>Book Space: {space.name}</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Username:</label>
                    <input
                        type="text"
                        className="form-control"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Date:</label>
                    <input
                        type="date"
                        className="form-control"
                        value={date}
                        onChange={(e) => setDate(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-success">Book</button>
            </form>
        </div>
    );
};

export default BookingFormPage;
