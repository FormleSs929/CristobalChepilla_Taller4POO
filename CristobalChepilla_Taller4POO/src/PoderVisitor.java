
/**
 * Implementacion del Visitor para calcular el poder de cada tipo de carta.
 * Contiene las formulas especificas de la pauta de evaluacion.
 * 
 */
public class PoderVisitor implements Visitor {

    @Override
    public double visit(Pokemon p) {
        if (p.getCantEnergias() == 0) return 0;
        return ((double) p.getDano() / p.getCantEnergias()) * 100;
    }

    @Override
    public double visit(Item i) {
        return i.getBonificacion() * 20;
    }

    @Override
    public double visit(Supporter s) {
        return s.getEfectosPorTurno() * 50;
    }

    @Override
    public double visit(Energy e) {
        return 1.0;
    }
}
