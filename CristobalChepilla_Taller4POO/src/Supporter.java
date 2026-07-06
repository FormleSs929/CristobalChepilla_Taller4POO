
/**
 * Representa una carta de tipo Supporter.
 */
public class Supporter extends Carta {
    private int efectosPorTurno;

    public Supporter(String nombre, int rareza, int efectosPorTurno) {
        super(nombre, rareza, "Supporter");
        this.efectosPorTurno = efectosPorTurno;
    }

    @Override
    public double aceptarPoder(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toFileFormat() {
        return getNombre() + ";" + getRareza() + ";" + getTipo() + ";" + efectosPorTurno;
    }

    public int getEfectosPorTurno() { return efectosPorTurno; }
    public void setEfectosPorTurno(int efectosPorTurno) { this.efectosPorTurno = efectosPorTurno; }
}