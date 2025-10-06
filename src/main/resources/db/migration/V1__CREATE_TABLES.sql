-- ============================================================================
-- V1__CREATE_TABLES.sql
-- Description: Initial database schema - Creates base users table
-- ============================================================================

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100),
    email VARCHAR(150) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    version INT DEFAULT 0,
    PRIMARY KEY (id)
);

-- Note: Table comments not supported in H2, skipping for development compatibility
