package controlador;
import modelo.Estadistica;
import modelo.EstadisticaDAO;
import modelo.Tablero;
import vista.Consola;
import java.util.List;
import java.util.Scanner;
public class Juego {
    private final Tablero tablero;
    private final Consola vista;
    private final EstadisticaDAO dao;
    private boolean juegoTerminado;
    public Juego() {
        tablero = new Tablero();
        vista = new Consola();
        dao = new EstadisticaDAO();
        juegoTerminado = false;
    }
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Buscaminas ---");
            System.out.println("1. Jugar");
            System.out.println("2. Ver estadísticas");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            String opcion = scanner.nextLine().trim();
            if (opcion.equals("3")) {
                dao.guardarEnCSV();
                System.out.println("¡Hasta luego!");
                break;
            }
            if (opcion.equals("2")) {
                mostrarEstadisticas();
                continue;
            }
            if (opcion.equals("1")) {
                System.out.print("Ingresa tu nombre: ");
                String nombre = scanner.nextLine().trim();
                jugarCon(nombre, scanner);
                tablero.reiniciar();
                juegoTerminado = false;
                continue;
            }
            System.out.println("Opción inválida. Intenta de nuevo.");
        }
        scanner.close();
    }
    private void mostrarEstadisticas() {
        List<Estadistica> lista = dao.listarTodas();
        if (lista.isEmpty()) {
            System.out.println("No hay estadísticas registradas aún.");
        } else {
            System.out.println("\nEstadísticas de jugadores:");
            lista.forEach(e -> System.out.println("  - " + e));
        }
    }
    private void jugarCon(String nombre, Scanner scanner) {
        Estadistica stats = dao.obtener(nombre);
        while (!juegoTerminado) {
            vista.mostrarTablero(tablero);
            System.out.print("Ingrese coordenada (ej. A5) o 'M A5' para marcar: ");
            String entrada = scanner.nextLine().toUpperCase().trim();
            try {
                boolean marcar = entrada.startsWith("M ");
                String coord = marcar ? entrada.substring(2) : entrada;

                int fila = coord.charAt(0) - 'A';
                int columna = Integer.parseInt(coord.substring(1)) - 1;

                if (!tablero.enRango(fila, columna)) {
                    System.out.println("Coordenadas fuera de rango.");
                    continue;
                }
                if (marcar) {
                    tablero.getCasilla(fila, columna).marcar();
                } else {
                    if (tablero.getCasilla(fila, columna).tieneMina()) {
                        tablero.getCasilla(fila, columna).descubrir();
                        vista.mostrarTablero(tablero);
                        System.out.println("💥 ¡Perdiste! Pisaste una mina.");
                        stats.sumarDerrota();
                        juegoTerminado = true;
                    } else {
                        tablero.descubrir(fila, columna);
                        if (tablero.todasDescubiertas()) {
                            vista.mostrarTablero(tablero);
                            System.out.println("🎉 ¡Ganaste! Descubriste todas las casillas seguras.");
                            stats.sumarVictoria();
                            juegoTerminado = true;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
            }
        }
        dao.guardarEnCSV();
    }
}