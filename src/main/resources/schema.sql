CREATE TABLE IF NOT EXISTS todo_line (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    item VARCHAR(200) NOT NULL,
    created TIMESTAMP NOT NULL,
    finished_by TIMESTAMP NULL
);