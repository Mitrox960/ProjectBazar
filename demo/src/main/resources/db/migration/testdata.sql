-- Script to create the initial schema for your database
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price DOUBLE NOT NULL,
                          description TEXT,
                          sold BOOLEAN NOT NULL,
                          user_id BIGINT,
                          FOREIGN KEY (user_id) REFERENCES users(id)
);
