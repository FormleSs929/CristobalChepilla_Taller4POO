
/**
 * Interfaz que define el contrato para el patron Visitor.
 * Permite separar los algoritmos de las clases sobre las que opera.
 * 
 * @author Tu Nombre
 * @version 1.0
 */
public interface Visitor {
    /** Visita una carta de tipo Pokemon */
    double visit(Pokemon pokemon);
    /** Visita una carta de tipo Item */
    double visit(Item item);
    /** Visita una carta de tipo Supporter */
    double visit(Supporter supporter);
    /** Visita una carta de tipo Energy */
    double visit(Energy energy);
}