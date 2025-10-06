-- ============================================================================
-- V3__ADD_OAUTH_FIELDS.sql
-- Description: Add OAuth and authentication related columns
-- ============================================================================

ALTER TABLE users ADD COLUMN password VARCHAR(100) DEFAULT NULL;
ALTER TABLE users ADD COLUMN google_id VARCHAR(255) DEFAULT NULL;
ALTER TABLE users ADD COLUMN provider VARCHAR(10) DEFAULT 'LOCAL' NOT NULL;
ALTER TABLE users ADD COLUMN profile_picture VARCHAR(500) DEFAULT NULL;
ALTER TABLE users ADD COLUMN enabled BOOLEAN DEFAULT TRUE NOT NULL;

-- Add unique constraint for google_id
ALTER TABLE users ADD CONSTRAINT uk_users_google_id UNIQUE (google_id);

-- Add indexes for OAuth fields
CREATE INDEX idx_users_google_id ON users(google_id);
CREATE INDEX idx_users_provider ON users(provider);