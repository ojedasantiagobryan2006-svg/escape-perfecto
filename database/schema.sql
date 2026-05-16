CREATE TABLE IF NOT EXISTS preguntas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    texto TEXT NOT NULL,
    opcion_a TEXT NOT NULL,
    opcion_b TEXT NOT NULL,
    opcion_c TEXT NOT NULL,
    respuesta_correcta TEXT NOT NULL,
    segundos INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS premios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    valor INTEGER NOT NULL,
    segundos_para_tomar INTEGER NOT NULL,
    escape_seguro INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS partidas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    jugador_preguntas TEXT NOT NULL,
    jugador_jaula TEXT NOT NULL,
    premio_total INTEGER NOT NULL,
    escapo INTEGER NOT NULL,
    fecha TEXT NOT NULL
);

INSERT INTO preguntas (texto, opcion_a, opcion_b, opcion_c, respuesta_correcta, segundos) VALUES
('¿Qué necesita el equipo para no perder los premios?', 'Salir antes de que cierre la puerta', 'Tomar solo premios pequeños', 'Responder sin mirar', 'A', 10),
('¿Qué representa el escape seguro?', 'Un premio que permite salir sin arriesgar todo', 'Una pregunta extra', 'Una penalización', 'A', 12),
('¿Qué pasa si el tiempo llega a cero dentro de la jaula?', 'Se duplican los premios', 'El jugador queda atrapado y pierde la ronda', 'Gana automáticamente', 'B', 15),
('¿Qué habilidad ayuda más en una sala de escape?', 'Resolver acertijos bajo presión', 'Esperar sin decidir', 'Ignorar pistas', 'A', 8);

INSERT INTO premios (nombre, valor, segundos_para_tomar, escape_seguro) VALUES
('Audífonos', 800, 5, 0),
('Tablet', 3500, 10, 0),
('Bicicleta', 5000, 14, 0),
('Escape seguro', 0, 6, 1);

