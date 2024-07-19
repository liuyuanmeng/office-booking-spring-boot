package Daisy.officeapp.service;

import Daisy.officeapp.model.Booking;
import Daisy.officeapp.model.Space;
import Daisy.officeapp.repository.BookingRepository;
import Daisy.officeapp.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SpaceRepository spaceRepository;


    public boolean canBook(Long spaceId, LocalDate date) {
        int bookingCount = bookingRepository.countBySpaceIdAndDate(spaceId, date);
        Space space = findSpaceById(spaceId);
        return bookingCount < space.getCapacity();
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Space findSpaceById(Long spaceId) {
        return spaceRepository.findById(spaceId)
                .orElseThrow(() -> new IllegalArgumentException("Space not found with id: " + spaceId));
    }

}
