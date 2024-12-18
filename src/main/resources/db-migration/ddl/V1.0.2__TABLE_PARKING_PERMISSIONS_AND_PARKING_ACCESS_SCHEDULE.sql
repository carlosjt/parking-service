-- Tabla para almacenar permisos de estacionamiento
CREATE TABLE parking_permissions (
                                     id SERIAL PRIMARY KEY,
                                     user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                                     category VARCHAR(20) NOT NULL,
                                     access_hours VARCHAR(50) NOT NULL,
                                     is_active BOOLEAN DEFAULT TRUE,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     CONSTRAINT chk_category_parking_permissions CHECK (category IN ('student', 'teacher', 'staff'))
);

-- Tabla para administrar horarios de acceso por categoría
CREATE TABLE parking_access_schedule (
                                         id SERIAL PRIMARY KEY,
                                         category VARCHAR(20) NOT NULL,
                                         day_of_week VARCHAR(15) NOT NULL,
                                         start_time TIME NOT NULL,
                                         end_time TIME NOT NULL,
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         CONSTRAINT chk_category_parking_access_schedule CHECK (category IN ('student', 'teacher', 'staff')),
                                         CONSTRAINT chk_day_of_week CHECK (day_of_week IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'))
);

-- Trigger para actualizar automáticamente el campo updated_at en ambas tablas
CREATE TRIGGER update_parking_permissions_timestamp
    BEFORE UPDATE ON parking_permissions
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_parking_access_schedule_timestamp
    BEFORE UPDATE ON parking_access_schedule
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();
