-- Tabla para definir áreas o sectores de estacionamiento
CREATE TABLE parking_areas (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(100) NOT NULL,
                               total_spaces INT NOT NULL CHECK (total_spaces > 0),
                               occupied_spaces INT DEFAULT 0 CHECK (occupied_spaces >= 0 AND occupied_spaces <= total_spaces),
                               location VARCHAR(100) NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla para registrar ocupación de espacios en tiempo real
CREATE TABLE parking_occupancy (
                                   id SERIAL PRIMARY KEY,
                                   parking_area_id INT NOT NULL REFERENCES parking_areas(id) ON DELETE CASCADE,
                                   vehicle_id INT REFERENCES vehicles(id) ON DELETE SET NULL,
                                   entry_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   exit_time TIMESTAMP,
                                   is_occupied BOOLEAN DEFAULT TRUE,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Trigger para actualizar automáticamente el campo updated_at en ambas tablas
CREATE TRIGGER update_parking_areas_timestamp
    BEFORE UPDATE ON parking_areas
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_parking_occupancy_timestamp
    BEFORE UPDATE ON parking_occupancy
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();
