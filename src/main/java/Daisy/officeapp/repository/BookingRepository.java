package Daisy.officeapp.repository;

import Daisy.officeapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
//The JpaRepository interface provides generic CRUD (Create, Read, Update, Delete)
// operations for the entity class that is being managed.
public interface BookingRepository extends JpaRepository<Booking, Long> {
// keep repository methods that might be useful in the future
    List<Booking> findBySpaceIdAndDate(Long spaceId, LocalDate date);
    List<Booking> findByDate(LocalDate date);
    int countBySpaceIdAndDate(Long spaceId, LocalDate date);
}
