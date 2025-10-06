-- ============================================================================
-- V5__INSERT_DATA.sql
-- Description: Insert initial seed data for development and testing
-- ============================================================================

-- Insert sample users with USER role
INSERT INTO users (name, lastname, email, provider, enabled, role_id, created_at) VALUES
('David', 'Galvez', 'david.galvez@example.com', 'LOCAL', TRUE, 1, NOW()),
('Rocio', 'Chavez', 'rocio.chavez@example.com', 'LOCAL', TRUE, 1, NOW()),
('Pedro', 'Acosta', 'pedro.acosta@example.com', 'LOCAL', TRUE, 1, NOW()),
('Marisol', 'Ugarte', 'marisol.ugarte@example.com', 'LOCAL', TRUE, 1, NOW()),
('Juan', 'Diaz', 'juan.diaz@example.com', 'LOCAL', TRUE, 1, NOW());

-- Insert admin user with hashed password (admin123)
INSERT INTO users (name, lastname, email, password, provider, enabled, role_id, created_at)
VALUES (
    'Admin',
    'User',
    'admin@hexagonal-demo.com',
    '$2a$12$gBpsIP1vjx4scbpkKgh8w.LA2n0zOie4S86mSJ6D/ByjKdAInZOG2',
    'LOCAL',
    TRUE,
    2,
    CURRENT_TIMESTAMP
);