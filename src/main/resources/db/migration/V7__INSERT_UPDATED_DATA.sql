-- ============================================================================
-- V7__INSERT_UPDATED_DATA.sql
-- Description: Insert updated seed data with new user fields
-- ============================================================================

-- Delete existing data first
DELETE FROM users;

-- Insert sample users with USER role (with new fields)
INSERT INTO users (name, apellido_paterno, apellido_materno, edad, dni, numero_telefonico, lastname, email, provider, enabled, role_id, created_at) VALUES
('David', 'Galvez', 'Perez', 30, '12345678', '987654321', 'Galvez', 'david.galvez@example.com', 'LOCAL', TRUE, 1, NOW()),
('Rocio', 'Chavez', 'Lopez', 25, '87654321', '987654322', 'Chavez', 'rocio.chavez@example.com', 'LOCAL', TRUE, 1, NOW()),
('Pedro', 'Acosta', 'Garcia', 35, '11223344', '987654323', 'Acosta', 'pedro.acosta@example.com', 'LOCAL', TRUE, 1, NOW()),
('Marisol', 'Ugarte', 'Rodriguez', 28, '44332211', '987654324', 'Ugarte', 'marisol.ugarte@example.com', 'LOCAL', TRUE, 1, NOW()),
('Juan', 'Diaz', 'Martinez', 40, '55667788', '987654325', 'Diaz', 'juan.diaz@example.com', 'LOCAL', TRUE, 1, NOW());

-- Insert admin user with hashed password (admin123)
INSERT INTO users (name, apellido_paterno, apellido_materno, edad, dni, numero_telefonico, lastname, email, password, provider, enabled, role_id, created_at)
VALUES (
    'Admin',
    'Admin',
    'User',
    45,
    '99999999',
    '987654326',
    'User',
    'admin@hexagonal-demo.com',
    '$2a$12$gBpsIP1vjx4scbpkKgh8w.LA2n0zOie4S86mSJ6D/ByjKdAInZOG2',
    'LOCAL',
    TRUE,
    2,
    CURRENT_TIMESTAMP
);

-- Note: Unique constraint for dni was already added in V6 migration