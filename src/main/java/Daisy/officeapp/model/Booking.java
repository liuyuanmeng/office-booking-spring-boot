package Daisy.officeapp.model;
//used to handle bidirectional relationships during JSON serialization and
// deserialization to avoid infinite recursion.
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

//specifies the name of the database table that this entity is mapped to
@Table(name = "bookings")

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private LocalDate date;

// multiple bookings can be associated with one space.
    @ManyToOne
    @JoinColumn(name = "space_id",  nullable = false)
    @JsonBackReference
    private Space space;
}
