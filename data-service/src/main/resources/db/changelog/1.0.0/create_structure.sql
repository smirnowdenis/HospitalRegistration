CREATE TABLE IF NOT EXISTS doctor
(
    id serial primary key,
    full_name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS registration
(
    id serial primary key,
    patient_full_name varchar(255) NOT NULL,
    doctor_id int,
    visit_date timestamp without time zone,
    foreign key(doctor_id) references doctor(id)
);

INSERT INTO doctor (full_name)
VALUES ('James Anderson'),
       ('Emily Johnson'),
       ('Michael Brown'),
       ('Sarah Davis'),
       ('Benjamin Carter'),
       ('Abigail Miller'),
       ('Ethan Robinson'),
       ('Sophia Thompson'),
       ('Alexander Harris'),
       ('Isabella Clark'),
       ('Joshua Wright'),
       ('Mia Lewis');

INSERT INTO registration (patient_full_name, doctor_id, visit_date)
VALUES ('Christopher Wilson', 1, '2025-03-17'),
       ('Jessica Taylor', 3, '2025-01-17'),
       ('Mattew Martinez', 2, '2025-02-06'),
       ('Ashley Moore', 4, '2025-03-12'),
       ('David Thomas', 1, '2025-02-25'),
       ('Olivia White', 4, '2025-01-27'),
       ('Daniel Hall', 1, '2025-03-17'),
       ('Chloe Adams', 4, '2025-01-17'),
       ('Lucas Mitchel', 5, '2025-02-06'),
       ('Harper Scott', 6, '2025-03-12'),
       ('Mason Perez', 7, '2025-02-25'),
       ('Ameli Edwards', 8, '2025-01-27'),
       ('Logan Evans', 9, '2025-02-06'),
       ('Grace Turner', 10, '2025-03-12'),
       ('Noah King', 11, '2025-02-25'),
       ('Lily Cooper', 12, '2025-01-27'),
       ('Christopher Wilson', 4, '2025-02-15'),
       ('Jessica Taylor', 8, '2025-01-25'),
       ('Mattew Martinez', 3, '2025-02-23'),
       ('Ashley Moore', 2, '2025-03-28'),
       ('David Thomas', 1, '2025-02-27'),
       ('Olivia White', 7, '2025-01-13'),
       ('Daniel Hall', 1, '2025-03-14'),
       ('Chloe Adams', 3, '2025-01-19'),
       ('Lucas Mitchel', 5, '2025-02-16'),
       ('Harper Scott', 6, '2025-03-10'),
       ('Mason Perez', 7, '2025-02-11'),
       ('Ameli Edwards', 2, '2025-01-27'),
       ('Logan Evans', 1, '2025-02-26'),
       ('Grace Turner', 3, '2025-03-14'),
       ('Noah King', 11, '2025-02-27'),
       ('Lily Cooper', 12, '2025-01-30');