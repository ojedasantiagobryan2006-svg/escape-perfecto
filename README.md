# Escape Perfecto

Juego de escritorio hecho en Java Swing para NetBeans, con base de datos SQLite y patron repositorio.

## Idea del juego

Un jugador responde preguntas para ganar segundos. Con esos segundos, el equipo intenta tomar premios de la jaula antes de que la puerta se cierre. El objetivo es acumular el mayor valor posible sin quedar atrapado.

## Tecnologias

- Java 17
- Java Swing
- SQLite
- Maven
- Patron Repository

## Como abrirlo en NetBeans

1. Abre NetBeans.
2. Selecciona `File > Open Project`.
3. Elige esta carpeta.
4. Ejecuta el proyecto con `Run Project`.

La interfaz principal esta en `src/main/java/com/escaperfecto/ui/GameFrame.java`.
NetBeans tambien puede abrir `GameFrame.form` para editar los componentes desde la pestana `Design`.

La base de datos `escape_perfecto.db` se crea automaticamente en la carpeta del proyecto la primera vez que se ejecuta.

## Como jugar

1. Escribe los nombres del participante que responde y del participante que entra a la jaula.
2. Elige una seccion de cultura general o deja `Todas`.
3. Presiona `Nueva partida`.
4. Responde preguntas para ganar segundos.
5. Usa esos segundos para tomar premios.
6. Puedes salir con `Escape seguro` o arriesgarte a seguir tomando premios.
7. Si el tiempo llega a cero antes de escapar, pierdes los premios de la partida.

## Secciones de preguntas

- Historia
- Geografia
- Ciencia y Naturaleza
- Arte y Literatura
- Entretenimiento y Cultura Pop
- Deportes

El juego incluye 30 preguntas de cultura general y 14 premios disponibles en la jaula.

## Comodines

Cada partida permite usar una vez cada comodin:

- `Cambiar seccion`: permite elegir otra seccion y cargar una pregunta nueva.
- `Eliminar opcion`: oculta una respuesta incorrecta de la pregunta actual.
- `Cambiar pregunta`: salta la pregunta actual y muestra la siguiente.

## Estructura

- `model`: clases del dominio del juego.
- `repository`: contratos y repositorios JDBC.
- `service`: logica principal de la partida.
- `ui`: interfaz grafica Swing.
- `db`: conexion e inicializacion de SQLite.


