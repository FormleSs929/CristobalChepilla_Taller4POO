
import java.util.Comparator;

/**
 * Interfaz base para las estrategias de ordenacion. 
 * Reutiliza Comparator de Java para acoplarse directamente a las colecciones.
 */
public interface OrdenamientoStrategy extends Comparator<Carta> {
    // Interfaz marcadora/extensora de Comparator
}