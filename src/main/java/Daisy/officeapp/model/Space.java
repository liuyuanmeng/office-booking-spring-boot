package Daisy.officeapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Table(name = "spaces")

@Entity
@Data
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//representing the capacity of the space.
    private int capacity;

    @Enumerated(EnumType.STRING)
    private SpaceSize size;

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL)
    @JsonManagedReference

    private List<Booking> bookings;

    public enum SpaceSize {
        SMALL, MEDIUM, LARGE, XLARGE
    }
}
