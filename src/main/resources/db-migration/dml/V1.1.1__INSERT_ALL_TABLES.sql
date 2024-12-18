INSERT INTO users (first_name, last_name, email, phone, user_type)
VALUES
    ('Alice', 'Smith', 'alice.smith@example.com', '1234567890', 'student'),
    ('Bob', 'Johnson', 'bob.johnson@example.com', '9876543210', 'teacher'),
    ('Charlie', 'Brown', 'charlie.brown@example.com', '555666777', 'staff');

INSERT INTO vehicles (user_id, license_plate, vehicle_type, brand, model, color)
VALUES
    (1, 'ABC123', 'car', 'Toyota', 'Corolla', 'Blue'),
    (2, 'XYZ789', 'motorcycle', 'Yamaha', 'R1', 'Black'),
    (3, 'LMN456', 'car', 'Honda', 'Civic', 'White');

INSERT INTO parking_permissions (user_id, category, access_hours, is_active)
VALUES
    (1, 'student', '08:00-18:00', TRUE),
    (2, 'teacher', '07:00-20:00', TRUE),
    (3, 'staff', '06:00-22:00', TRUE);

INSERT INTO parking_access_schedule (category, day_of_week, start_time, end_time)
VALUES
    ('student', 'Monday', '08:00', '18:00'),
    ('teacher', 'Monday', '07:00', '20:00'),
    ('staff', 'Monday', '06:00', '22:00'),
    ('student', 'Tuesday', '08:00', '18:00'),
    ('teacher', 'Tuesday', '07:00', '20:00'),
    ('staff', 'Tuesday', '06:00', '22:00');

INSERT INTO access_control (vehicle_id, entry_time, exit_time, is_authorized)
VALUES
    (1, '2024-12-17 08:00:00', '2024-12-17 12:00:00', TRUE),
    (2, '2024-12-17 09:00:00', NULL, TRUE),
    (3, '2024-12-17 10:00:00', '2024-12-17 14:00:00', FALSE);

INSERT INTO access_devices (device_type, location, is_active)
VALUES
    ('license_plate_reader', 'Main Gate', TRUE),
    ('id_card_reader', 'East Gate', TRUE),
    ('license_plate_reader', 'West Gate', TRUE);

INSERT INTO parking_areas (name, total_spaces, occupied_spaces, location)
VALUES
    ('Main Parking Lot', 100, 10, 'North Campus'),
    ('Staff Parking Lot', 50, 5, 'South Campus'),
    ('Visitor Parking Lot', 20, 2, 'East Campus');

INSERT INTO parking_occupancy (parking_area_id, vehicle_id, entry_time, exit_time, is_occupied)
VALUES
    (1, 1, '2024-12-17 08:00:00', NULL, TRUE),
    (2, 2, '2024-12-17 09:30:00', NULL, TRUE),
    (3, 3, '2024-12-17 10:00:00', '2024-12-17 14:00:00', FALSE);

INSERT INTO parking_reservations (user_id, parking_area_id, reservation_date, start_time, end_time, status, is_special_event)
VALUES
    (1, 1, '2024-12-18', '09:00', '12:00', 'confirmed', FALSE),
    (2, 2, '2024-12-18', '08:00', '11:00', 'pending', TRUE),
    (3, 3, '2024-12-19', '10:00', '13:00', 'cancelled', FALSE);

INSERT INTO special_events (name, description, event_date, start_time, end_time, parking_area_id)
VALUES
    ('Open Day', 'Special parking for university open day.', '2024-12-20', '08:00', '16:00', 1),
    ('Faculty Meeting', 'Reserved parking for faculty meeting.', '2024-12-21', '09:00', '12:00', 2);
