# Buscaminas en Consola - Proyecto Universitario en Java
Hola, soy Carlos Guerrero, estudiante de Ingeniería en Desarrollo de Software, y este proyecto es parte de mi examen final de la materia **Programación Orientada a Objetos (POO)**.
Se trata de una versión del juego **Buscaminas**, totalmente funcional desde consola, desarrollada aplicando los principios fundamentales de POO y buenas prácticas de desarrollo.
## Objetivos
- Aplicar los conceptos de **clases, objetos, herencia, polimorfismo y encapsulamiento**.
- Utilizar el patrón **MVC (Modelo-Vista-Controlador)** para estructurar el código.
- Manejar excepciones propias y predefinidas.
- Guardar el estado del juego mediante **persistencia de datos**.
- Seguir principios de **código limpio** y usar **GitHub** para control de versiones.
## Funcionalidades
- Menú para Jugar, Ver estadísticas y Salir.
- Registro de nombre de jugador antes de cada partida.
- Guardado de victorias y derrotas en estadisticas.csv.
- Historial completo de jugadores en consola.
- Método reiniciar() en Tablero para múltiples rondas.
## Arquitectura y Diseño
He aplicado la técnica DRY para evitar duplicación de lógica en el renderizado del tablero y en la expansión recursiva de casillas seguras. Se emplean nombres de métodos descriptivos y funciones cortas para mejorar la legibilidad. Durante el desarrollo, se realizaron refactorizaciones continuas:
- Separación de responsabilidades entre clases de interfaz, lógica de juego y persistencia.
- Simplificación de métodos complejos mediante extracción de procedimientos auxiliares.
Esta organización modular sienta las bases para incorporar en el futuro pruebas unitarias con JUnit y Mockito.
## Cómo ejecutar
1. Clona este repositorio desde Apache Netbeans, ten en cuenta que tienes que organizar el codigo en carpetas dependiendo de su funcion guiado por el diagrama de clases:
https://github.com/CarlosJordano/ExamenFinal.git
