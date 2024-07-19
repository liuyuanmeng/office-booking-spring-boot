import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
// useState, useEffect from React: These hooks are used to manage state and side effects in the component.
// useNavigate from react-router-dom: This hook is used for navigation between routes.
// axios: This library is used to make HTTP requests.

const SpaceListPage = () => {
// spaces is a state variable initialized as an empty array, used to store the list of spaces fetched from the API.
    const [spaces, setSpaces] = useState([]);
// navigate is a function provided by useNavigate to programmatically navigate to different routes.
    const navigate = useNavigate();

    const fetchSpaces = () => {
        axios.get('http://localhost:8080/api/spaces')
            .then(response => {
                setSpaces(response.data);
            })
            .catch(error => {
                console.error('Error fetching spaces:', error);
            });
    };
// This hook calls the fetchSpaces function once when the component mounts (i.e., when the component is first rendered).
// The empty dependency array ([]) ensures that the effect runs only once.
    useEffect(() => {
        fetchSpaces();
    }, []);
// This function navigates to the booking page for a specific space when the "Book this space" button is clicked.
// It takes spaceId as an argument and uses the navigate function to redirect to / book with the spaceId as a query parameter.

    const handleBookSpace = (spaceId) => {
        navigate(`/book?spaceId=${spaceId}`);
    };
// The component returns a JSX structure that represents the UI.
    return (
        <div className="container">
            <h2>Available Spaces</h2>
            <ul className="list-group">
                {spaces.map(space => {
                    const remainingCapacity = space.capacity - space.bookings.length;
                    return (
                        <li key={space.id} className="list-group-item">
                            <div>{space.name} (Capacity: {space.capacity})</div>
                            <div>Remaining Capacity: {remainingCapacity}</div>
                            <ul>
                                {space.bookings.map(booking => (
                                    <li key={booking.id}>
                                        {booking.username} booked on {booking.date}
                                    </li>
                                ))}
                            </ul>
                            <button onClick={() => handleBookSpace(space.id)} className="btn btn-primary">
                                Book this space
                            </button>
                        </li>
                    );
                })}
            </ul>
        </div>
    );
};

export default SpaceListPage;
