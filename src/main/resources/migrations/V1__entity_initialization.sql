INSERT INTO genre (genre_name)
VALUES ('Classic'),
       ('Mystery'),
       ('Romance'),
       ('Science Fiction'),
       ('Historical Fiction');


INSERT INTO country (country_name)
VALUES ('United States'),
       ('United Kingdom'),
       ('France'),
       ('Russia'),
       ('Germany');

INSERT INTO author (firstname, lastname, birth_date, country_id)
VALUES ('Jane', 'Austen', '1775-12-16', 2),
       ('Leo', 'Tolstoy', '1828-09-09', 4),
       ('Charles', 'Dickens', '1812-02-07', 1),
       ('Mary', 'Shelley', '1797-08-30', 3),
       ('Herman', 'Melville', '1819-08-01', 1);


INSERT INTO book (title, count_of_pages, publication_year, isbn, genre_id, author_id)
VALUES ('Pride and Prejudice', 279, 1813, '978-5-17-152338-1', 1, 1),
       ('War and Peace', 1225, 1869, '978-1-85326-062-9', 1, 2),
       ('Great Expectations', 505, 1861, '978-1-5098-2536-3', 1, 3),
       ('Frankenstein', 280, 1818, '978-0-14-143947-1', 4, 4),
       ('Moby-Dick', 585, 1851, '978-1-4715-1169-1', 1, 5);




