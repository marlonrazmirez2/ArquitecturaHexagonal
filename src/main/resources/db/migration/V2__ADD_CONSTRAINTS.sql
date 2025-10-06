-- ============================================================================
-- V2__ADD_CONSTRAINTS.sql
-- Description: Add data integrity constraints and indexes to users table
-- ============================================================================

-- Unique constraint on email
ALTER TABLE users ADD CONSTRAINT uk_users_email UNIQUE (email);

-- Check constraint for name minimum length
ALTER TABLE users ADD CONSTRAINT ck_users_name_length
    CHECK (CHAR_LENGTH(TRIM(name)) >= 2);

-- Index on email for faster lookups (moved from V4 to avoid duplication)
CREATE INDEX idx_users_email ON users(email);

-- Index on name for searching
CREATE INDEX idx_users_name ON users(name);