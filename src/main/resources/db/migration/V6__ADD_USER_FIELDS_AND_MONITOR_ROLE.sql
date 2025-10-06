-- ============================================================================
-- V6__ADD_USER_FIELDS_AND_MONITOR_ROLE.sql
-- Description: Add new user fields and MONITOR role
-- ============================================================================

-- Add new user fields
ALTER TABLE users ADD COLUMN apellido_paterno VARCHAR(100) NOT NULL DEFAULT '';
ALTER TABLE users ADD COLUMN apellido_materno VARCHAR(100) NOT NULL DEFAULT '';
ALTER TABLE users ADD COLUMN edad INT NOT NULL DEFAULT 0;
ALTER TABLE users ADD COLUMN dni VARCHAR(8) NOT NULL DEFAULT '';
ALTER TABLE users ADD COLUMN numero_telefonico VARCHAR(9) NOT NULL DEFAULT '';

-- Note: Unique constraint for dni will be added in V7 after data migration

-- Insert MONITOR role
INSERT INTO roles (name, description) VALUES
('MONITOR', 'Monitor with permissions to search by age');

-- Add indexes for new fields
CREATE INDEX idx_users_apellido_paterno ON users(apellido_paterno);
CREATE INDEX idx_users_apellido_materno ON users(apellido_materno);
CREATE INDEX idx_users_edad ON users(edad);
CREATE INDEX idx_users_dni ON users(dni);
CREATE INDEX idx_users_numero_telefonico ON users(numero_telefonico);