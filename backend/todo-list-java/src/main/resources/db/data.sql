CREATE TABLE tasks (
                      id         BIGINT PRIMARY KEY,
                      name       VARCHAR(255) NOT NULL,
                      description VARCHAR(255),
                      completed  BOOLEAN default false,
                      week_day   VARCHAR(255) NOT NULL
);
