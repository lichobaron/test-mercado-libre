CREATE TABLE user_item
(
    user_id BIGINT,
    item_id BIGINT,
    PRIMARY KEY (user_id, item_id),
    FOREIGN KEY (user_id) REFERENCES User (id),
    FOREIGN KEY (item_id) REFERENCES Item (id)
);