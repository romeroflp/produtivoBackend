CREATE TABLE reset_token(
    token UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id),
    expired_at TIMESTAMP NOT NULL,
    is_used BOOLEAN
);