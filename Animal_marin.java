/**
 * Classe abstraite représentant un animal marin dans la simulation.
 *
 * <p>Un animal marin est une {@link Ressource} dont la population évolue
 * automatiquement à chaque étape de la simulation, sans intervention d'un agent
 * extérieur. Chaque sous-classe définit sa propre loi d'évolution via {@link #evoluer()}.</p>
 *
 * <p>Exemples de sous-classes : {@link Sardine}, {@link Dauphine}, {@link Requin}.</p>
 *
 * @see Ressource
 * @see Sardine
 * @see Dauphine
 * @see Requin
 */
public abstract class Animal_marin extends Ressource {

    /**
     * Construit un animal marin avec un type et une population initiale.
     *
     * @param type     nom de l'espèce (ex. {@code "Requin"}, {@code "Sardine"}) ;
     *                 ne doit pas être {@code null} ni vide
     * @param quantite population initiale ; doit être &gt;= 0
     * @throws IllegalArgumentException si {@code type} est {@code null}/vide
     *                                  ou si {@code quantite} est négative
     */
    public Animal_marin(String type, int quantite) {
        super(type, quantite);
        if (type == null || type.isEmpty())
            throw new IllegalArgumentException("Le type d'animal ne peut pas être null ou vide.");
        if (quantite < 0)
            throw new IllegalArgumentException("La quantité initiale ne peut pas être négative : " + quantite);
    }

    /**
     * Fait évoluer la population de cet animal pour une étape de simulation.
     *
     * <p>Chaque sous-classe définit sa propre logique (croissance, décroissance,
     * prédation, etc.). Cette méthode est appelée automatiquement par
     * {@link Simulation#etape(int)} après les actions des bateaux.</p>
     */
    public abstract void evoluer();

    /**
     * Retourne une représentation textuelle de cet animal marin.
     *
     * @return chaîne de la forme {@code Animal_marin[...]}
     */
    @Override
    public String toString() {
        return "Animal_marin[" + super.toString() + "]";
    }
}
