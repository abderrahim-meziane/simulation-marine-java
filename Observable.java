/**
 * Interface représentant un élément observable dans la simulation marine.
 *
 * <p>Tout élément pouvant être ciblé par un {@link Touristique} doit implémenter
 * cette interface. Elle permet au bateau touristique de dépendre d'une abstraction
 * plutôt que des classes concrètes ({@link Dauphine}, {@link Requin}).</p>
 *
 * <p>Principe appliqué : <em>programmation par interface</em> — le bateau
 * touristique ne connaît que {@code Observable}, pas les types concrets.</p>
 *
 * @see Touristique
 * @see Dauphine
 * @see Requin
 */
public interface Observable {

    /**
     * Retourne une description textuelle de cet élément observable.
     *
     * @return description lisible (ex. {@code "Dauphin(s) : 5 individu(s)"})
     */
    String getDescription();

    /**
     * Retourne la valeur d'intérêt touristique de cet élément.
     *
     * <p>Plus la valeur est élevée, plus l'élément est attractif pour un
     * {@link Touristique}. La valeur doit être &gt;= 0.</p>
     *
     * @return valeur d'intérêt (&gt;= 0)
     */
    int getInteret();
}
