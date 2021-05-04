-- Table for mapping users
CREATE TABLE users (
                       id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;

-- Table for mapping roles
CREATE TABLE roles (
                       id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL
)
    ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
                            user_id INT NOT NULL,
                            role_id INT NOT NULL,

                            FOREIGN KEY (user_id) REFERENCES users (id),
                            FOREIGN KEY (role_id) REFERENCES roles (id),

                            UNIQUE (user_id, role_id)
)
    ENGINE = InnoDB;

INSERT INTO users VALUES (1, 'admin', 'admin', 'admin','admin');
INSERT INTO users VALUES (2, 'user', 'user', 'user','user');

INSERT INTO roles VALUES (1, 'admin');
INSERT INTO roles VALUES (2, 'user');

INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (2, 2);