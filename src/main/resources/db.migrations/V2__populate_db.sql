INSERT INTO Client (name) VALUES
                              ('John Smith'),
                              ('Jane Doe'),
                              ('Michael Johnson'),
                              ('Emma Davis'),
                              ('Oliver Wilson');

INSERT INTO Planet (id, name) VALUES
                                  ('MARS', 'Mars'),
                                  ('VEN', 'Venus'),
                                  ('JUP', 'Jupiter'),
                                  ('SAT', 'Saturn'),
                                  ('URAN', 'Uranus');

INSERT INTO Ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
                                                                             ('2023-03-10 10:30:00', 1, 'MARS', 'VEN'),
                                                                             ('2023-03-10 11:00:00', 2, 'VEN', 'MARS'),
                                                                             ('2023-03-10 12:15:00', 3, 'JUP', 'URAN'),
                                                                             ('2023-03-10 13:30:00', 4, 'SAT', 'JUP'),
                                                                             ('2023-03-10 14:00:00', 5, 'URAN', 'SAT');