CREATE TABLE Reservas (
    searchId SERIAL PRIMARY KEY,
    hotel_id VARCHAR(20) NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    edades JSONB
);

INSERT INTO Reservas (hotel_id, check_in, check_out, edades)
VALUES ('123abc', '2023-12-29', '2023-12-31', '[3, 29, 30, 1]');

INSERT INTO Reservas (hotel_id, check_in, check_out, edades)
VALUES ('456def', '2023-12-30', '2023-12-31', '[25, 40, 50]');

INSERT INTO Reservas (hotel_id, check_in, check_out, edades)
VALUES ('789ghi', '2023-12-25', '2023-12-30', '[10, 15, 20]');

INSERT INTO Reservas (hotel_id, check_in, check_out, edades)
VALUES ('123jkl', '2023-12-27', '2023-12-29', '[5, 35, 45]');

INSERT INTO Reservas (hotel_id, check_in, check_out, edades)
VALUES ('456mno', '2023-12-29', '2023-12-31', '[30, 35, 40]');