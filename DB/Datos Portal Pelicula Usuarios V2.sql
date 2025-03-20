-- Insertar datos en authorities
INSERT INTO `PortalPeliculasUsuario`.`authorities` (`authority`) VALUES 
('ADMIN'),
('USER');

-- Insertar datos en users
INSERT INTO `PortalPeliculasUsuario`.`users` (`username`, `email`, `password`, `enabled`, `authority_id`) VALUES
('admin', 'admin@portal.com', '$2a$10$aPRCLwomPRymvBvJ4IWBNeFAewqi41CVxb4hXNhoO9KOpPjq/fLQW', TRUE, 1),
('usuario1', 'usuario1@portal.com', '$2a$10$HsigqaewL8NB36CG0r5mPeAmJ1EAl2r67PbMR0IniDUztaCVZPlyC', TRUE, 2),
('usuario2', 'usuario2@portal.com', '$2a$10$QUKG2mOm4aggibOuJXhdWuuOSkypF5OXHp0M9pz5.dvuSjKlzkgOa', TRUE, 2);

-- Insertar datos en criticas
INSERT INTO `PortalPeliculasUsuario`.`criticas` (`peliculaId`, `userId`, `valoracion`, `nota`) VALUES
(1, 2, 'Una película increíble, con una trama envolvente.', 9),
(1, 3, 'Buena cinematografía, pero la historia pudo ser mejor.', 7),
(2, 2, 'Un clásico imperdible, actuación magistral.', 10),
(3, 3, 'No me convenció, esperaba más del director.', 5);
