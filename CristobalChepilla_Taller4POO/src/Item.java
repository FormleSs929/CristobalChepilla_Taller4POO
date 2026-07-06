
/**
 * Representa una carta de tipo Item.
 */
public class Item extends Carta {
    private int bonificacion;

    public Item(String nombre, int rareza, int bonificacion) {
        super(nombre, rareza, "Item");
        this.bonificacion = bonificacion;
    }

    @Override
    public double aceptarPoder(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toFileFormat() {
        return getNombre() + ";" + getRareza() + ";" + getTipo() + ";" + bonificacion;
    }

    public int getBonificacion() { return bonificacion; }
    public void setBonificacion(int bonificacion) { this.bonificacion = bonificacion; }
}