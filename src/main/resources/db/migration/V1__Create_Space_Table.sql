DROP TABLE IF EXISTS spaces;

CREATE TABLE spaces (
id bigserial PRIMARY KEY,    name VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    size VARCHAR(255) NOT NULL
);

