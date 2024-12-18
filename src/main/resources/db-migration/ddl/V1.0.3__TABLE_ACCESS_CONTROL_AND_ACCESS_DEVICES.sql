-- Tabla para registrar accesos (entradas y salidas) de vehículos
CREATE TABLE access_control (
                                id SERIAL PRIMARY KEY,
                                vehicle_id INT NOT NULL REFERENCES vehicles(id) ON DELETE CASCADE,
                                entry_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                exit_time TIMESTAMP,
                                is_authorized BOOLEAN DEFAULT TRUE,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla para administrar dispositivos de lectura (lectores de matrícula o tarjetas)
CREATE TABLE access_devices (
                                id SERIAL PRIMARY KEY,
                                device_type VARCHAR(50) NOT NULL,
                                location VARCHAR(100) NOT NULL,
                                is_active BOOLEAN DEFAULT TRUE,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT chk_device_type CHECK (device_type IN ('license_plate_reader', 'id_card_reader'))
);

-- Trigger para actualizar automáticamente el campo updated_at en ambas tablas
CREATE TRIGGER update_access_control_timestamp
    BEFORE UPDATE ON access_control
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_access_devices_timestamp
    BEFORE UPDATE ON access_devices
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();
