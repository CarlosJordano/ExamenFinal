package modelo;

import java.util.Random;

public class Tablero {
    private final Casilla[][] casillas;
    private final int filas = 10;
    private final int columnas = 10;
    private final int minas = 10;

    public Tablero() {
        casillas = new Casilla[filas][columnas];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                casillas[i][j] = new Casilla();

        colocarMinas();
        calcularMinasAdyacentes();
    }

    private void colocarMinas() {
        Random rand = new Random();
        int colocadas = 0;
        while (colocadas < minas) {
            int f = rand.nextInt(filas);
            int c = rand.nextInt(columnas);
            if (!casillas[f][c].tieneMina()) {
                casillas[f][c].ponerMina();
                colocadas++;
            }
        }
    }

    private void calcularMinasAdyacentes() {
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                if (!casillas[i][j].tieneMina()) {
                    int contador = 0;
                    for (int dx = -1; dx <= 1; dx++)
                        for (int dy = -1; dy <= 1; dy++) {
                            int ni = i + dx;
                            int nj = j + dy;
                            if (enRango(ni, nj) && casillas[ni][nj].tieneMina())
                                contador++;
                        }
                    casillas[i][j].setMinasAdyacentes(contador);
                }
    }

    public boolean enRango(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public void descubrir(int fila, int columna) {
        Casilla casilla = casillas[fila][columna];
        if (casilla.estaDescubierta() || casilla.estaMarcada()) return;

        casilla.descubrir();
        if (casilla.getMinasAdyacentes() == 0 && !casilla.tieneMina()) {
            for (int dx = -1; dx <= 1; dx++)
                for (int dy = -1; dy <= 1; dy++) {
                    int ni = fila + dx;
                    int nj = columna + dy;
                    if (enRango(ni, nj))
                        descubrir(ni, nj);
                }
        }
    }

    public boolean todasDescubiertas() {
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++) {
                Casilla c = casillas[i][j];
                if (!c.tieneMina() && !c.estaDescubierta())
                    return false;
            }
        return true;
    }

    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
}
