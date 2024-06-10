create schema int103;

-- Trains
CREATE TABLE trains (
    trainNumber INT PRIMARY KEY,
    route VARCHAR(100) NOT NULL,
    departureTime VARCHAR(50) NOT NULL
);

-- Tickets
CREATE TABLE tickets (
    ticketId INT  AUTO_INCREMENT PRIMARY KEY,
    trainNumber INT NOT NULL,
    travelDate datetime DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (trainNumber) REFERENCES trains(trainNumber)
);

-- Trains
INSERT INTO trains (trainNumber, route, departureTime) VALUES (101, 'Bangkok - Chiang Mai', '2024-06-07 15:30:00');
INSERT INTO trains (trainNumber, route, departureTime) VALUES (102, 'Bangkok - Hua Hin', '2024-06-07 18:00:00');
INSERT INTO trains (trainNumber, route, departureTime) VALUES (103, 'Bangkok - Pattaya', '2024-06-07 20:45:00');

-- Tickets
INSERT INTO tickets (trainNumber) VALUES (101);
INSERT INTO tickets (trainNumber) VALUES (102);
INSERT INTO tickets (trainNumber) VALUES (103);


commit;

