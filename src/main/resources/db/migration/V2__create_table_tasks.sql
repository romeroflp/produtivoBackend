CREATE TABLE tasks (
       id BIGSERIAL PRIMARY KEY,
       user_id UUID REFERENCES users(id),
       title VARCHAR(255) NOT NULL,
       description TEXT,
       created_in TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       expiration_date TIMESTAMP,
       category VARCHAR(100) NOT NULL,
       status_task VARCHAR(100) NOT NULL,
       recurrent BOOLEAN NOT NULL

);