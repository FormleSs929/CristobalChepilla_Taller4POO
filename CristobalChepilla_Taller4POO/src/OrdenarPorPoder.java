
/**
 * Ordena de Mayor a Menor Poder calculado mediante el Visitor.
 */
public class OrdenarPorPoder implements OrdenamientoStrategy {
    private final PoderVisitor poderVisitor = new PoderVisitor();

    @Override
    public int compare(Carta c1, Carta c2) {
        double p1 = c1.aceptarPoder(poderVisitor);
        double p2 = c2.aceptarPoder(poderVisitor);
        return Double.compare(p2, p1);
    }
}