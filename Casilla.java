package modelo;

public class Casilla {
    private boolean tieneMina;
    private boolean descubierta;
    private boolean marcada;
    private int minasAdyacentes;

    public Casilla() {
        this.tieneMina = false;
        this.descubierta = false;
        this.marcada = false;
        this.minasAdyacentes = 0;
    }

    public boolean tieneMina() { return tieneMina; }
    public void ponerMina() { this.tieneMina = true; }

    public boolean estaDescubierta() { return descubierta; }
    public void descubrir() { this.descubierta = true; }

    public boolean estaMarcada() { return marcada; }
    public void marcar() { this.marcada = !this.marcada; }

    public int getMinasAdyacentes() { return minasAdyacentes; }
    public void setMinasAdyacentes(int cantidad) { this.minasAdyacentes = cantidad; }

    public String mostrar() {
        if (marcada) return "F";
        if (!descubierta) return "#";
        if (tieneMina) return "X";
        if (minasAdyacentes > 0) return String.valueOf(minasAdyacentes);
        return " ";
    }
}
