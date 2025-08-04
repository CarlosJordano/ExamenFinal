package vista;

import modelo.Tablero;

public class Consola {
    public void mostrarTablero(Tablero tablero) {
        System.out.println("    1 2 3 4 5 6 7 8 9 10");
        char letra = 'A';
        for (int i = 0; i < tablero.getFilas(); i++) {
            System.out.print(" " + letra++ + "  ");
            for (int j = 0; j < tablero.getColumnas(); j++) {
                System.out.print(tablero.getCasilla(i, j).mostrar() + " ");
            }
            System.out.println();
        }
    }
}
