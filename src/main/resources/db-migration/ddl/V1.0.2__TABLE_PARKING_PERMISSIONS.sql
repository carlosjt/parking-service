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

-- Trigger para actualizar automáticamente el campo updated_at en ambas tablas
CREATE TRIGGER update_parking_permissions_timestamp
    BEFORE UPDATE ON parking_permissions
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();
