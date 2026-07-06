
/**
 * Representa una carta de tipo Energy.
 */
public class Energy extends Carta {
    private String elemento;

    public Energy(String nombre, int rareza, String elemento) {
        super(nombre, rareza, "Energy");
        this.elemento = elemento;
    }

    @Override
    public double aceptarPoder(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toFileFormat() {
        return getNombre() + ";" + getRareza() + ";" + getTipo() + ";" + elemento;
    }

    public String getElemento() { return elemento; }
    public void setElemento(String elemento) { this.elemento = elemento; }
}