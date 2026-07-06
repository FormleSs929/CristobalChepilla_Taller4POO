//Cristobal Nicolas Chepilla Arriagada, 21873055-8 ITI
import javax.swing.SwingUtilities;

/**
 * Punto de entrada principal de la aplicacion que separa el Sistema
 * de la inicializacion del entorno visual.
 * 
 */
public class App {
    public static void main(String[] args) {
        // Ejecucion en el hilo de despacho de eventos de Swing por seguridad de hilos
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
