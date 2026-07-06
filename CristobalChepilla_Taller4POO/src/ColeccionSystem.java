
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Controladora del Sistema bajo el Patron Singleton.
 * Gestiona el almacenamiento en memoria y la persistencia simple de archivos.
 * 
 * @author Tu Nombre
 * @version 1.0
 */
public class ColeccionSystem {
    private static ColeccionSystem instance;
    private final ArrayList<Carta> coleccion;
    private final String archivoRuta = "Sobres.txt";

    private ColeccionSystem() {
        this.coleccion = new ArrayList<>();
        cargarArchivo();
    }

    /**
     * Obtiene la unica instancia del sistema.
     */
    public static ColeccionSystem getInstance() {
        if (instance == null) {
            instance = new ColeccionSystem();
        }
        return instance;
    }

    /**
     * Carga las cartas desde el archivo de texto usando Scanner de forma simple.
     */
    private void cargarArchivo() {
        File f = new File(archivoRuta);
        if (!f.exists()) return;

        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) continue;

                // Split simple por punto y coma
                String[] partes = linea.split(";");
                String nombre = partes[0];
                int rareza = Integer.parseInt(partes[1]);
                String tipo = partes[2];

                // Extraemos el resto de parametros especificos
                String[] extras = new String[partes.length - 3];
                System.arraycopy(partes, 3, extras, 0, extras.length);

                // Instanciacion limpia via Factory
                Carta nueva = CartaFactory.crearCarta(tipo, nombre, rareza, extras);
                if (nueva != null) {
                    coleccion.add(nueva);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Guarda la coleccion completa al archivo .txt sobreescribiendolo en orden.
     */
    public void guardarArchivo() {
        try (PrintWriter pw = new PrintWriter(new File(archivoRuta))) {
            for (Carta c : coleccion) {
                pw.println(c.toFileFormat());
            }
        } catch (Exception e) {
            System.err.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    public void agregarCarta(Carta c) {
        coleccion.add(c);
        guardarArchivo();
    }

    public void eliminarCarta(Carta c) {
        coleccion.remove(c);
        guardarArchivo();
    }

    public ArrayList<Carta> getColeccion() {
        return new ArrayList<>(coleccion); // Copia para proteger integridad
    }

    /**
     * Ordena la lista de cartas de la coleccion usando una estrategia dada.
     */
    public void ordenarColeccion(OrdenamientoStrategy estrategia) {
        coleccion.sort(estrategia);
    }
}