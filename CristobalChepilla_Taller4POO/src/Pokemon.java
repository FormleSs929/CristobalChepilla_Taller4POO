
/**
 * Representa una carta de tipo Pokemon.
 */
public class Pokemon extends Carta {
    private int dano;
    private int cantEnergias;

    public Pokemon(String nombre, int rareza, int dano, int cantEnergias) {
        super(nombre, rareza, "Pokemon");
        this.dano = dano;
        this.cantEnergias = cantEnergias;
    }

    @Override
    public double aceptarPoder(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toFileFormat() {
        return getNombre() + ";" + getRareza() + ";" + getTipo() + ";" + dano + ";" + cantEnergias;
    }

    public int getDano() { return dano; }
    public void setDano(int dano) { this.dano = dano; }
    public int getCantEnergias() { return cantEnergias; }
    public void setCantEnergias(int cantEnergias) { this.cantEnergias = cantEnergias; }
}