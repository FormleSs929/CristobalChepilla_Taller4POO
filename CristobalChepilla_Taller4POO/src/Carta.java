
/**
 * Clase abstracta que representa una Carta base del juego TCG.
 * 
 */
public abstract class Carta {
    private String nombre;
    private int rareza;
    private String tipo;

    /**
     * Constructor base para cualquier carta.
     * 
     * @param nombre Nombre de la carta.
     * @param rareza Nivel de rareza (mientras mayor, mejor).
     * @param tipo Categoria o tipo de carta.
     */
    public Carta(String nombre, int rareza, String tipo) {
        this.nombre = nombre;
        this.rareza = rareza;
        this.tipo = tipo;
    }

    /**
     * Metodo abstracto para aceptar la visita de un algoritmo (Visitor).
     * 
     * @param visitor El visitor que operara sobre la carta.
     * @return El poder calculado por el visitor.
     */
    public abstract double aceptarPoder(Visitor visitor);

    /**
     * Devuelve la representacion de la carta lista para guardarse en el .txt.
     * 
     * @return String con formato Nombre;Rareza;Tipo;...
     */
    public abstract String toFileFormat();

    public String getNombre() { return nombre; }
    public int getRareza() { return rareza; }
    public String getTipo() { return tipo; }

    @Override
    public String toString() {
        return nombre + " [" + tipo + "] - Rareza: " + rareza;
    }
}
