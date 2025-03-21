create database touristguide;
 use touristguide;

CREATE TABLE cities (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        city_name VARCHAR(100) UNIQUE NOT NULL
);


CREATE TABLE attractions (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(100) NOT NULL,
                             description TEXT,
                             city_id INT,
                             FOREIGN KEY (city_id) REFERENCES cities(id) ON DELETE SET NULL
);


CREATE TABLE tags (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      tag_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE attraction_tags (
                                 attraction_id INT,
                                 tag_id INT,
                                 PRIMARY KEY (attraction_id, tag_id),
                                 FOREIGN KEY (attraction_id) REFERENCES attractions(id) ON DELETE CASCADE,
                                 FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);