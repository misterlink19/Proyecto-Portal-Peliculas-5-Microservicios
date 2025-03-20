-- Inserciones en la tabla 'peliculas'
INSERT INTO `portalpeliculas`.`peliculas` (`titulo`, `annio`, `direccion`, `genero`, `sinopsis`, `duracion`, `imagen_Portada`, `pais`) VALUES 
('Star Wars: Episode IV - A New Hope', 1977, 'George Lucas', 'Ciencia Ficción', 'Un joven granjero llamado Luke Skywalker se une a una princesa rebelde, un contrabandista y sus amigos robots para destruir una estación espacial imperial capaz de destruir planetas.', 121, 'a_new_hope.jpg', 'Estados Unidos'),
('Star Wars: Episode V - The Empire Strikes Back', 1980, 'Irvin Kershner', 'Ciencia Ficción', 'Luke Skywalker aprende más sobre la Fuerza mientras sus amigos son perseguidos por el malvado Darth Vader, culminando en un enfrentamiento épico.', 124, 'empire_strikes_back.jpg', 'Estados Unidos'),
('The Godfather', 1972, 'Francis Ford Coppola', 'Drama', 'La crónica de la familia Corleone bajo el patriarcado de Vito Corleone, centrada en la transformación de su hijo Michael de un forastero reticente a un despiadado jefe mafioso.', 175, 'godfather.jpg', 'Estados Unidos'),
('The Shawshank Redemption', 1994, 'Frank Darabont', 'Drama', 'La historia de Andy Dufresne, un banquero condenado a cadena perpetua por un crimen que no cometió, y su inesperada amistad con el recluso Red en la prisión de Shawshank.', 142, 'shawshank.jpg', 'Estados Unidos'),
('Pulp Fiction', 1994, 'Quentin Tarantino', 'Crimen', 'Una serie de historias entrelazadas sobre dos matones, un boxeador, la esposa de un gánster y otros personajes en Los Ángeles.', 154, 'pulp_fiction.jpg', 'Estados Unidos'),
('The Silence of the Lambs', 1991, 'Jonathan Demme', 'Thriller', 'Una joven agente del FBI busca la ayuda de un astuto asesino caníbal encarcelado para atrapar a otro asesino en serie conocido como Buffalo Bill.', 118, 'silence_lambs.jpg', 'Estados Unidos'),
('Schindler\'s List', 1993, 'Steven Spielberg', 'Drama', 'La historia real de Oskar Schindler, un empresario alemán que salvó a más de mil refugiados judíos del Holocausto durante la Segunda Guerra Mundial.', 195, 'schindlers_list.jpg', 'Estados Unidos'),
('The Lion King', 1994, 'Roger Allers, Rob Minkoff', 'Animación', 'La epopeya de un joven león africano llamado Simba que debe reclamar su derecho de nacimiento y convertirse en rey tras la muerte de su padre en manos de su tío.', 88, 'lion_king.jpg', 'Estados Unidos'),
('Gladiator', 2000, 'Ridley Scott', 'Acción', 'Un general romano traicionado busca venganza contra el corrupto emperador que asesinó a su familia y lo condenó a la esclavitud.', 155, 'gladiator.jpg', 'Estados Unidos'),
('Interstellar', 2014, 'Christopher Nolan', 'Ciencia Ficción', 'Un grupo de astronautas viaja a través de un agujero de gusano en busca de un nuevo hogar para la humanidad mientras enfrentan dilemas morales y desafíos científicos.', 169, 'interstellar.jpg', 'Estados Unidos');

-- Inserciones en la tabla 'actores'
INSERT INTO `portalpeliculas`.`actores` (`nombre`, `fecha_nacimiento`, `pais`) VALUES 
('Mark Hamill', '1951-09-25', 'Estados Unidos'),
('Harrison Ford', '1942-07-13', 'Estados Unidos'),
('Carrie Fisher', '1956-10-21', 'Estados Unidos'),
('Alec Guinness', '1914-04-02', 'Reino Unido'),
('James Earl Jones', '1931-01-17', 'Estados Unidos'),
('David Prowse', '1935-07-01', 'Reino Unido'),
('Marlon Brando', '1924-04-03', 'Estados Unidos'),
('Al Pacino', '1940-04-25', 'Estados Unidos'),
('James Caan', '1940-03-26', 'Estados Unidos'),
('Robert Duvall', '1931-01-05', 'Estados Unidos'),
('Diane Keaton', '1946-01-05', 'Estados Unidos'),
('Talia Shire', '1946-04-25', 'Estados Unidos'),
('Tim Robbins', '1958-10-16', 'Estados Unidos'),
('Morgan Freeman', '1937-06-01', 'Estados Unidos'),
('Bob Gunton', '1945-11-15', 'Estados Unidos'),
('William Sadler', '1950-04-13', 'Estados Unidos'),
('Clancy Brown', '1959-01-05', 'Estados Unidos'),
('Gil Bellows', '1967-06-28', 'Canadá'),
('John Travolta', '1954-02-18', 'Estados Unidos'),
('Samuel L. Jackson', '1948-12-21', 'Estados Unidos'),
('Uma Thurman', '1970-04-29', 'Estados Unidos'),
('Bruce Willis', '1955-03-19', 'Alemania'),
('Ving Rhames', '1959-05-12', 'Estados Unidos'),
('Harvey Keitel', '1939-05-13', 'Estados Unidos'),
('Jodie Foster', '1962-11-19', 'Estados Unidos'),
('Anthony Hopkins', '1937-12-31', 'Reino Unido'),
('Scott Glenn', '1941-01-26', 'Estados Unidos'),
('Ted Levine', '1957-05-29', 'Estados Unidos'),
('Brooke Smith', '1967-05-22', 'Estados Unidos'),
('Anthony Heald', '1944-08-25', 'Estados Unidos'),
('Liam Neeson', '1952-06-07', 'Reino Unido'),
('Ben Kingsley', '1943-12-31', 'Reino Unido'),
('Ralph Fiennes', '1962-12-22', 'Reino Unido'),
('Caroline Goodall', '1959-11-13', 'Reino Unido'),
('Jonathan Sagall', '1959-04-23', 'Israel'),
('Matthew Broderick', '1962-03-21', 'Estados Unidos'),
('Jeremy Irons', '1948-09-19', 'Reino Unido'),
('Moira Kelly', '1968-03-06', 'Estados Unidos'),
('Nathan Lane', '1956-02-03', 'Estados Unidos'),
('Russell Crowe', '1964-04-07', 'Nueva Zelanda'),
('Joaquin Phoenix', '1974-10-28', 'Puerto Rico'),
('Connie Nielsen', '1965-07-03', 'Dinamarca'),
('Oliver Reed', '1938-02-13', 'Reino Unido'),
('Derek Jacobi', '1938-10-22', 'Reino Unido'),
('Djimon Hounsou', '1964-04-24', 'Benín'),
('Matthew McConaughey', '1969-11-04', 'Estados Unidos'),
('Anne Hathaway', '1982-11-12', 'Estados Unidos'),
('Jessica Chastain', '1977-03-24', 'Estados Unidos'),
('Michael Caine', '1933-03-14', 'Reino Unido'),
('Bill Irwin', '1950-04-11', 'Estados Unidos'),
('Tom Hanks', '1956-07-09', 'Estados Unidos');

-- Inserciones en la tabla 'reparto' con claves externas desactivadas temporalmente
SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO `portalpeliculas`.`reparto` (`idPelicula`, `idActor`) VALUES 
(1, 1), -- Star Wars: Episode IV - A New Hope, Mark Hamill
(1, 2), -- Star Wars: Episode IV - A New Hope, Harrison Ford
(1, 3), -- Star Wars: Episode IV - A New Hope, Carrie Fisher
(1, 4), -- Star Wars: Episode IV - A New Hope, Alec Guinness
(1, 5), -- Star Wars: Episode IV - A New Hope, James Earl Jones
(1, 6), -- Star Wars: Episode IV - A New Hope, David Prowse
(2, 1), -- Star Wars: Episode V - The Empire Strikes Back, Mark Hamill
(2, 2), -- Star Wars: Episode V - The Empire Strikes Back, Harrison Ford
(2, 3), -- Star Wars: Episode V - The Empire Strikes Back, Carrie Fisher
(2, 5), -- Star Wars: Episode V - The Empire Strikes Back, James Earl Jones
(2, 7), -- Star Wars: Episode V - The Empire Strikes Back, Marlon Brando
(2, 8), -- Star Wars: Episode V - The Empire Strikes Back, Al Pacino
(3, 7), -- The Godfather, Marlon Brando
(3, 8), -- The Godfather, Al Pacino
(3, 9), -- The Godfather, James Caan
(3, 10), -- The Godfather, Robert Duvall
(3, 11), -- The Godfather, Diane Keaton
(3, 12), -- The Godfather, Talia Shire
(4, 13), -- The Shawshank Redemption, Tim Robbins
(4, 14), -- The Shawshank Redemption, Morgan Freeman
(4, 15), -- The Shawshank Redemption, Bob Gunton
(4, 16), -- The Shawshank Redemption, William Sadler
(4, 17), -- The Shawshank Redemption, Clancy Brown
(4, 18), -- The Shawshank Redemption, Gil Bellows
(5, 19), -- Pulp Fiction, John Travolta
(5, 20), -- Pulp Fiction, Samuel L. Jackson
(5, 21), -- Pulp Fiction, Uma Thurman
(5, 22), -- Pulp Fiction, Bruce Willis
(5, 23), -- Pulp Fiction, Ving Rhames
(5, 24), -- Pulp Fiction, Harvey Keitel
(6, 25), -- The Silence of the Lambs, Jodie Foster
(6, 26), -- The Silence of the Lambs, Anthony Hopkins
(6, 27), -- The Silence of the Lambs, Scott Glenn
(6, 28), -- The Silence of the Lambs, Ted Levine
(6, 29), -- The Silence of the Lambs, Brooke Smith
(6, 30), -- The Silence of the Lambs, Anthony Heald
(7, 31), -- Schindler's List, Liam Neeson
(7, 32), -- Schindler's List, Ben Kingsley
(7, 33), -- Schindler's List, Ralph Fiennes
(7, 34), -- Schindler's List, Caroline Goodall
(7, 35), -- Schindler's List, Jonathan Sagall
(7, 36), -- Schindler's List, Matthew Broderick
(8, 37), -- The Lion King, Jeremy Irons
(8, 38), -- The Lion King, James Earl Jones
(8, 39), -- The Lion King, Moira Kelly
(8, 40), -- The Lion King, Nathan Lane
(8, 41), -- The Lion King, Matthew Broderick
(8, 42), -- The Lion King, Jeremy Irons
(9, 43), -- Gladiator, Russell Crowe
(9, 44), -- Gladiator, Joaquin Phoenix
(9, 45), -- Gladiator, Connie Nielsen
(9, 46), -- Gladiator, Oliver Reed
(9, 47), -- Gladiator, Derek Jacobi
(9, 48), -- Gladiator, Djimon Hounsou
(10, 49), -- Interstellar, Matthew McConaughey
(10, 50), -- Interstellar, Anne Hathaway
(10, 51), -- Interstellar, Jessica Chastain
(10, 52), -- Interstellar, Michael Caine
(10, 53), -- Interstellar, Bill Irwin
(10, 54); -- Interstellar, Tom Hanks
-- Vuelve a activar las comprobaciones de claves externas
SET FOREIGN_KEY_CHECKS = 1;