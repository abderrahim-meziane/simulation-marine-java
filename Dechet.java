/**
 * Ressource statique représentant un déchet marin (plastique, filet, etc.).
 *
 * <p>Contrairement aux {@link Animal_marin}s, un déchet n'évolue pas
 * spontanément : sa quantité ne change que si un agent (un {@link Netoyeur})
 * intervient pour le retirer du terrain.</p>
 *
 * @see Ressource
 * @see Netoyeur
 */
public class Dechet extends Ressource {

    /**
     * Construit un déchet marin avec un type et une quantité initiale.
     *
     * @param type     catégorie du déchet (ex. {@code "Plastique"}, {@code "Filet"})
     * @param quantite quantité initiale (doit être &gt;= 0)
     */
    public Dechet(String type, int quantite) {
        super(type, quantite);
    }

    /**
     * Constructeur par copie : crée un {@code Dechet} identique à l'original.
     *
     * @param autre le {@code Dechet} à copier ; ne doit pas être {@code null}
     * @throws IllegalArgumentException si {@code autre} est {@code null}
     */
    public Dechet(Dechet autre) {
        super(autre != null ? autre.type : null, autre != null ? autre.getQuantite() : 0);
        if (autre == null)
            throw new IllegalArgumentException("Le Dechet à copier ne peut pas être null.");
    }

    /**
     * Retourne une représentation textuelle de ce déchet.
     *
     * @return chaîne de la forme {@code Dechet[...]}
     */
    @Override
    public String toString() {
        return "Dechet[" + super.toString() + "]";
    }
}
