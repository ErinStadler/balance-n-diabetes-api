DROP TABLE IF EXISTS blood_sugar;

CREATE TABLE blood_sugar (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    user_id BIGINT NOT NULL,
    count INT NOT NULL,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX user_ind (user_id),
        FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
