package controlador;

import modelo.Tablero;
import vista.Consola;

import java.util.Scanner;

public class Juego {
    private final Tablero tablero;
    private final Consola vista;
    private boolean juegoTerminado;

    public Juego() {
        tablero = new Tablero();
        vista = new Consola();
        juegoTerminado = false;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
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
                        juegoTerminado = true;
                    } else {
                        tablero.descubrir(fila, columna);
                        if (tablero.todasDescubiertas()) {
                            vista.mostrarTablero(tablero);
                            System.out.println("🎉 ¡Ganaste! Descubriste todas las casillas seguras.");
                            juegoTerminado = true;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
            }
        }
        scanner.close();
    }
}
