package modelo;
public class Estadistica {
    private String nombre;
    private int ganadas;
    private int perdidas;
    public Estadistica(String nombre, int ganadas, int perdidas) {
        this.nombre = nombre;
        this.ganadas = ganadas;
        this.perdidas = perdidas;
    }
    public String getNombre() {
        return nombre;
    }
    public int getGanadas() {
        return ganadas;
    }
    public int getPerdidas() {
        return perdidas;
    }
    public void sumarVictoria() {
        this.ganadas++;
    }
    public void sumarDerrota() {
        this.perdidas++;
    }
    @Override
    public String toString() {
        return nombre + ": Ganadas=" + ganadas + ", Perdidas=" + perdidas;
    }
}