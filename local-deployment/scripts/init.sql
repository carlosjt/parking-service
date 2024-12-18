CREATE USER ps_app WITH PASSWORD 'PS@123!';
GRANT ALL PRIVILEGES ON DATABASE parking_service to ps_app;
\connect parking_service
CREATE SCHEMA parking_service_core;
GRANT ALL PRIVILEGES ON SCHEMA parking_service_core to ps_app;
ALTER DATABASE parking_service SET search_path TO parking_service_core;
DROP SCHEMA public CASCADE;
