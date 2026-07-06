
/**
 * Ordena de Mayor a Menor rareza (mientras mayor, mejor).
 */
public class OrdenarPorRareza implements OrdenamientoStrategy {
    @Override
    public int compare(Carta c1, Carta c2) {
        return Integer.compare(c2.getRareza(), c1.getRareza());
    }
}