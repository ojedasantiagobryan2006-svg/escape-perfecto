CREATE TABLE IF NOT EXISTS preguntas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    categoria VARCHAR(80) NOT NULL DEFAULT 'General',
    texto VARCHAR(255) NOT NULL,
    opcion_a VARCHAR(160) NOT NULL,
    opcion_b VARCHAR(160) NOT NULL,
    opcion_c VARCHAR(160) NOT NULL,
    respuesta_correcta CHAR(1) NOT NULL,
    segundos INT NOT NULL
);

CREATE TABLE IF NOT EXISTS premios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(120) NOT NULL,
    valor INT NOT NULL,
    segundos_para_tomar INT NOT NULL,
    escape_seguro BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS partidas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    jugador_preguntas VARCHAR(100) NOT NULL,
    jugador_jaula VARCHAR(100) NOT NULL,
    premio_total INT NOT NULL,
    escapo BOOLEAN NOT NULL,
    fecha VARCHAR(40) NOT NULL
);

INSERT INTO preguntas (categoria, texto, opcion_a, opcion_b, opcion_c, respuesta_correcta, segundos) VALUES
('Historia', 'En que anio cayo el Imperio Romano de Occidente?', '476 d.C.', '1492', '1789', 'A', 10),
('Historia', 'Quien fue el primer presidente de los Estados Unidos?', 'Abraham Lincoln', 'George Washington', 'Thomas Jefferson', 'B', 10),
('Historia', 'Que acontecimiento de 1789 marca el inicio de la Edad Contemporanea?', 'La Revolucion Francesa', 'La Primera Guerra Mundial', 'La caida de Constantinopla', 'A', 12),
('Historia', 'Que civilizacion antigua construyo Machu Picchu?', 'Los mayas', 'Los incas', 'Los egipcios', 'B', 10),
('Historia', 'Quien fue conocida como La dama de hierro?', 'Indira Gandhi', 'Margaret Thatcher', 'Marie Curie', 'B', 8),
('Geografia', 'Cual es el rio mas largo y caudaloso del mundo?', 'Rio Nilo', 'Rio Amazonas', 'Rio Yangtse', 'B', 12),
('Geografia', 'Cual es la capital de Australia?', 'Sydney', 'Melbourne', 'Canberra', 'C', 10),
('Geografia', 'Que pais tiene la mayor extension territorial del mundo?', 'Canada', 'Rusia', 'China', 'B', 10),
('Geografia', 'En que continente se encuentra el desierto de Atacama?', 'Africa', 'America del Sur', 'Asia', 'B', 8),
('Geografia', 'Cual es el oceano mas grande de la Tierra?', 'Atlantico', 'Indico', 'Pacifico', 'C', 10),
('Ciencia y Naturaleza', 'Cual es el elemento quimico mas abundante en el universo?', 'Oxigeno', 'Hidrogeno', 'Carbono', 'B', 12),
('Ciencia y Naturaleza', 'Que organo del cuerpo humano consume la mayor cantidad de energia?', 'Corazon', 'Cerebro', 'Pulmones', 'B', 10),
('Ciencia y Naturaleza', 'Como se llama la fuerza que atrae los objetos al centro de la Tierra?', 'Magnetismo', 'Gravedad', 'Friccion', 'B', 8),
('Ciencia y Naturaleza', 'Cual es el unico mamifero capaz de volar de forma activa?', 'Ardilla voladora', 'Murcielago', 'Colugo', 'B', 10),
('Ciencia y Naturaleza', 'Que gas absorbe gran parte de la radiacion ultravioleta del sol?', 'Ozono', 'Nitrogeno', 'Helio', 'A', 12),
('Arte y Literatura', 'Quien escribio Cien anios de soledad?', 'Gabriel Garcia Marquez', 'Mario Vargas Llosa', 'Julio Cortazar', 'A', 12),
('Arte y Literatura', 'Que pintor renacentista es autor de La Gioconda?', 'Miguel Angel', 'Leonardo da Vinci', 'Rafael', 'B', 10),
('Arte y Literatura', 'Como se llama el miedo irracional a los libros o a la lectura?', 'Claustrofobia', 'Bibliofobia', 'Acrofobia', 'B', 12),
('Arte y Literatura', 'En que ciudad italiana esta el fresco La ultima cena?', 'Roma', 'Florencia', 'Milan', 'C', 10),
('Arte y Literatura', 'Quien es el autor de Romeo y Julieta?', 'William Shakespeare', 'Charles Dickens', 'Victor Hugo', 'A', 8),
('Entretenimiento y Cultura Pop', 'Que pelicula de 1997 dirigida por James Cameron gano 11 premios Oscar?', 'Avatar', 'Titanic', 'Terminator 2', 'B', 12),
('Entretenimiento y Cultura Pop', 'De que banda era vocalista Freddie Mercury?', 'The Beatles', 'Queen', 'Pink Floyd', 'B', 10),
('Entretenimiento y Cultura Pop', 'Quien creo el universo de Star Wars?', 'George Lucas', 'Steven Spielberg', 'James Cameron', 'A', 10),
('Entretenimiento y Cultura Pop', 'Cual es el videojuego mas vendido de todos los tiempos?', 'Minecraft', 'Tetris', 'Grand Theft Auto V', 'A', 12),
('Entretenimiento y Cultura Pop', 'Que banda surcoreana se convirtio en fenomeno mundial de K-pop?', 'BTS', 'Coldplay', 'One Direction', 'A', 8),
('Deportes', 'Cada cuantos anios se celebran los Juegos Olimpicos de Verano?', 'Cada 2 anios', 'Cada 4 anios', 'Cada 6 anios', 'B', 8),
('Deportes', 'Que tenista masculino tiene el record de mas Grand Slam ganados?', 'Roger Federer', 'Rafael Nadal', 'Novak Djokovic', 'C', 12),
('Deportes', 'En que deporte se usa un objeto llamado puck o disco?', 'Hockey sobre hielo', 'Rugby', 'Beisbol', 'A', 10),
('Deportes', 'Que seleccion ha ganado mas Copas del Mundo de la FIFA?', 'Alemania', 'Brasil', 'Argentina', 'B', 10),
('Deportes', 'Cuanto mide una piscina olimpica oficial?', '25 metros', '50 metros', '100 metros', 'B', 8);

INSERT INTO premios (nombre, valor, segundos_para_tomar, escape_seguro) VALUES
('Audifonos', 800, 5, 0),
('Tablet', 3500, 10, 0),
('Bicicleta', 5000, 14, 0),
('Escape seguro', 0, 6, 1),
('Smartwatch', 1200, 6, 0),
('Consola de videojuegos', 7500, 18, 0),
('Laptop', 12000, 22, 0),
('Bocina bluetooth', 900, 5, 0),
('Camara deportiva', 2800, 9, 0),
('Mochila gamer', 1500, 7, 0),
('Tarjeta de regalo', 1000, 4, 0),
('Viaje sorpresa', 15000, 25, 0),
('Telefono celular', 9000, 20, 0),
('Pantalla LED', 10000, 21, 0);
