USE avm;

CREATE TABLE user(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(500) NOT NULL,
    email VARCHAR(50) NOT NULL
);
CREATE TABLE file(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    path VARCHAR(500) NOT NULL,
    size VARCHAR(500) NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT fk_userid FOREIGN KEY (user_id) REFERENCES user(id)
);

SET character_set_client = utf8;
SET character_set_connection = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;
