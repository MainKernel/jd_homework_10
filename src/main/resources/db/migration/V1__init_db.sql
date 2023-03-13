CREATE TABLE Client (
                        id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(200) NOT NULL CHECK (LENGTH(name) >= 3)
);

CREATE TABLE Planet (
                        id VARCHAR(20) PRIMARY KEY CHECK (id REGEXP '^[A-Z0-9]+$'),
                        name VARCHAR(500) NOT NULL
);

CREATE TABLE Ticket (
                        id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        client_id INTEGER NOT NULL,
                        from_planet_id VARCHAR(20) NOT NULL,
                        to_planet_id VARCHAR(20) NOT NULL,
                        FOREIGN KEY (client_id) REFERENCES Client(id) ON DELETE CASCADE,
                        FOREIGN KEY (from_planet_id) REFERENCES Planet(id) ON DELETE CASCADE ,
                        FOREIGN KEY (to_planet_id) REFERENCES Planet(id) ON DELETE CASCADE
);