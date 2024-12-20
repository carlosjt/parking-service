-- Tabla para almacenar eventos especiales relacionados con reservas
CREATE TABLE special_events (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(100) NOT NULL,
                                description TEXT,
                                event_date DATE NOT NULL,
                                start_time TIME NOT NULL,
                                end_time TIME NOT NULL,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT check_event_time_range CHECK (end_time > start_time)
);

-- Tabla para gestionar las reservas de espacios de estacionamiento
CREATE TABLE parking_reservations (
                                      id SERIAL PRIMARY KEY,
                                      user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                                      parking_area_id INT NOT NULL REFERENCES parking_areas(id) ON DELETE CASCADE,
                                      special_event_id INT REFERENCES special_events(id) ON DELETE CASCADE,
                                      reservation_date DATE NOT NULL,
                                      start_time TIME NOT NULL,
                                      end_time TIME NOT NULL,
                                      status VARCHAR(20) DEFAULT 'PENDING',
                                      is_special_event BOOLEAN DEFAULT FALSE,
                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      CONSTRAINT check_time_range CHECK (end_time > start_time),
                                      CONSTRAINT chk_status CHECK (status IN ('PENDING', 'CONFIRMED', 'CANCELLED'))
);

-- Trigger para actualizar automáticamente el campo updated_at en ambas tablas
CREATE TRIGGER update_parking_reservations_timestamp
    BEFORE UPDATE ON parking_reservations
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_special_events_timestamp
    BEFORE UPDATE ON special_events
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();
