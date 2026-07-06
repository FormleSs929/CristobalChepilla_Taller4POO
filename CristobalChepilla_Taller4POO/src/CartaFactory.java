
/**
 * Fabrica encargada de centralizar la creacion de instancias de Cartas
 * segun su tipo correspondiente de forma limpia.
 * 
 * @author Tu Nombre
 * @version 1.0
 */
public class CartaFactory {

    /**
     * Construye y retorna la subclase adecuada de Carta.
     * 
     * @param tipo El tipo de carta ("Pokemon", "Item", "Supporter", "Energy").
     * @param nombre Nombre de la carta.
     * @param rareza Rareza numerica.
     * @param extras Parametros adicionales especificos de la carta en formato String.
     * @return Una instancia concreta de la Carta o null si el tipo no es valido.
     */
    public static Carta crearCarta(String tipo, String nombre, int rareza, String[] extras) {
        switch (tipo) {
            case "Pokemon":
                int dano = Integer.parseInt(extras[0]);
                int cantEnergias = Integer.parseInt(extras[1]);
                return new Pokemon(nombre, rareza, dano, cantEnergias);
                
            case "Item":
                int bonif = Integer.parseInt(extras[0]);
                return new Item(nombre, rareza, bonif);
                
            case "Supporter":
                int efectos = Integer.parseInt(extras[0]);
                return new Supporter(nombre, rareza, efectos);
                
            case "Energy":
                String elemento = extras[0];
                return new Energy(nombre, rareza, elemento);
                
            default:
                return null;
        }
    }
}