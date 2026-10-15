ALTER TABLE tasks
ALTER COLUMN category_id TYPE BIGINT USING category_id::BIGINT;

ALTER TABLE tasks
    ADD CONSTRAINT fkey_category_id
        FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL;