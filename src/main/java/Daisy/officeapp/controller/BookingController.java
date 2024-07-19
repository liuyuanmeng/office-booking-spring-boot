package Daisy.officeapp.controller;

import Daisy.officeapp.model.Booking;
import Daisy.officeapp.model.Space;
import Daisy.officeapp.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;

// The @RestControllerSpring automatically serializes the return objects from your request handling methods to JSON
//(or other appropriate formats) and writes them directly to the HTTP response.
//When use @RestController, Spring treats every method in this controller as if it is annotated with @ResponseBody.
//This means that the return values of your methods will be written directly to the HTTP response body.
@RestController
//Automatically applies @ResponseBody to all methods, meaning the return type will be written directly to the HTTP response body as JSON (or other configured formats).
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;

    @PostMapping
//@RequestParam Used to extract query parameters from the URL and bind them to method parameters.
    public ResponseEntity<Booking> createBooking(@RequestParam String username,
                                                 @RequestParam String date,
                                                 @RequestParam Long spaceId) {
        logger.info("Received request to create booking: username={}, date={}, spaceId={}", username, date, spaceId);
        try {
            // Parse the date string into a LocalDate object
            LocalDate parsedDate = LocalDate.parse(date);
            // Retrieve the Space object by its ID
            Space space = bookingService.findSpaceById(spaceId);
            // Check if a booking can be made for the given space and date
            if (bookingService.canBook(spaceId, parsedDate)) {
                // Create a new Booking object and set its properties
                Booking booking = new Booking();
                booking.setUsername(username);
                booking.setDate(parsedDate);
                booking.setSpace(space);

                Booking createdBooking = bookingService.createBooking(booking);
//used to log server-side events
                logger.info("Successfully created booking: {}", createdBooking);
                return ResponseEntity.created(URI.create("/api/bookings/" + createdBooking.getId())).body(createdBooking);
            } else {
                logger.warn("Booking capacity reached for the selected date and space: spaceId={}", spaceId);
                return ResponseEntity.status(400).body(null);
            }
        } catch (Exception e) {
            logger.error("Error creating booking: username={}, date={}, spaceId={}", username, date, spaceId, e);
            return ResponseEntity.status(500).build();
        }
    }
}
