

public class OrdenarPorNombre implements OrdenamientoStrategy {
    @Override
    public int compare(Carta c1, Carta c2) {
        return c1.getNombre().compareToIgnoreCase(c2.getNombre());
    }
}