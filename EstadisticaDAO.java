package modelo;
import java.io.*;
import java.util.*;
public class EstadisticaDAO {
    private static final String RUTA_CSV = "estadisticas.csv";
    private final Map<String, Estadistica> cache = new HashMap<>();
    public EstadisticaDAO() {
        cargarDesdeCSV();
    }
    private void cargarDesdeCSV() {
        File archivo = new File(RUTA_CSV);
        if (!archivo.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] cols = linea.split(",");
                if (cols.length == 3) {
                    String nombre = cols[0];
                    int gan = Integer.parseInt(cols[1]);
                    int per = Integer.parseInt(cols[2]);
                    cache.put(nombre, new Estadistica(nombre, gan, per));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar estadísticas: " + e.getMessage());
        }
    }
    public Estadistica obtener(String nombre) {
        return cache.computeIfAbsent(nombre, n -> new Estadistica(n, 0, 0));
    }
    public List<Estadistica> listarTodas() {
        return new ArrayList<>(cache.values());
    }
    public void guardarEnCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_CSV))) {
            for (Estadistica e : cache.values()) {
                String linea = String.join(",",
                    e.getNombre(),
                    String.valueOf(e.getGanadas()),
                    String.valueOf(e.getPerdidas())
                );
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar estadísticas: " + e.getMessage());
        }
    }
}