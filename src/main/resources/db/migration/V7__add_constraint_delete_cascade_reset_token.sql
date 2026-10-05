ALTER TABLE reset_token ADD CONSTRAINT reset_token_user_id_fkey
FOREIGN KEY (user_id) REFERENCES users(id)
ON DELETE CASCADE;