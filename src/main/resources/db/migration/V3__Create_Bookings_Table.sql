-- Drop table if it exists
DROP TABLE IF EXISTS bookings;

-- Create bookings table
CREATE TABLE bookings (
    id bigserial PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    space_id BIGINT,
    CONSTRAINT fk_space
        FOREIGN KEY(space_id)
        REFERENCES spaces(id)
);


