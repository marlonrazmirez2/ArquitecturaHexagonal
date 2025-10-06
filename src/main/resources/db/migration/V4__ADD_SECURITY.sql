-- ============================================================================
-- V4__ADD_SECURITY.sql
-- Description: Add role-based access control (RBAC) system
-- ============================================================================

-- Create roles table
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert default roles
INSERT INTO roles (name, description) VALUES
('USER', 'Regular user with basic permissions'),
('ADMIN', 'Administrator with full permissions');

-- Add role_id and last_login to users table
ALTER TABLE users ADD COLUMN role_id BIGINT DEFAULT 1;
ALTER TABLE users ADD COLUMN last_login TIMESTAMP NULL;
ALTER TABLE users ADD CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles(id);

-- Add indexes for security fields
CREATE INDEX idx_users_role_id ON users(role_id);
CREATE INDEX idx_users_last_login ON users(last_login);