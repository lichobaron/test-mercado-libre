CREATE TABLE item
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    mId   VARCHAR(255) NOT NULL,
    name  VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    INDEX idx_mId (mId)
);

CREATE TABLE user
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    mId   VARCHAR(255) NOT NULL,
    name  VARCHAR(255) NOT NULL,
    INDEX idx_mId (mId)
);

CREATE TABLE user_item
(
    user_id BIGINT,
    item_id BIGINT,
    PRIMARY KEY (user_id, item_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);