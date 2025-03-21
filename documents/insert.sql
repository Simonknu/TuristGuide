INSERT INTO cities (city_name) VALUES
                                   ('Copenhagen'),
                                   ('Aarhus'),
                                   ('Odense');

INSERT INTO tags (tag_name) VALUES
                                ('free'),
                                ('nature'),
                                ('kids friendly'),
                                ('museum'),
                                ('art'),
                                ('monument');


-- id is auto-increment, city_id is FK from cities table
INSERT INTO attractions (name, description, city_id) VALUES
                                                         ('The Little Mermaid', 'Iconic bronze statue by the water', 1),
                                                         ('Tivoli Gardens', 'Historic amusement park in the city center', 1),
                                                         ('ARoS Art Museum', 'Modern art museum with rainbow rooftop', 2),
                                                         ('The Old Town', 'Open-air museum showcasing Danish history', 2),
                                                         ('Odense Zoo', 'Popular zoo with many exotic animals', 3),
                                                         ('Hans Christian Andersen Museum', 'Museum dedicated to the famous fairy tale writer', 3);


-- The Little Mermaid
INSERT INTO attraction_tags (attraction_id, tag_id) VALUES
                                                        (1, 1), -- free
                                                        (1, 6); -- monument

-- Tivoli Gardens
INSERT INTO attraction_tags (attraction_id, tag_id) VALUES
                                                        (2, 3), -- kids friendly
                                                        (2, 1); -- free (if free entry on some days, optional)

-- ARoS Art Museum
INSERT INTO attraction_tags (attraction_id, tag_id) VALUES
                                                        (3, 4), -- museum
                                                        (3, 5); -- art

-- The Old Town (Aarhus)
INSERT INTO attraction_tags (attraction_id, tag_id) VALUES
                                                        (4, 4), -- museum
                                                        (4, 6); -- monument

-- Odense Zoo
INSERT INTO attraction_tags (attraction_id, tag_id) VALUES
                                                        (5, 3), -- kids friendly
                                                        (5, 2); -- nature

-- Hans Christian Andersen Museum
INSERT INTO attraction_tags (attraction_id, tag_id) VALUES
                                                        (6, 4), -- museum
                                                        (6, 5); -- art