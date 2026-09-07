import java.util.ArrayList;

/**
 * Bateau touristique : localise l'animal marin le plus intéressant à observer
 * ({@link Observable} avec le plus grand {@link Observable#getInteret()}) et
 * s'en approche à chaque étape.
 *
 * <p>À chaque appel de {@link #agir()}, le bateau :</p>
 * <ol>
 *   <li>Parcourt toutes les ressources du terrain.</li>
 *   <li>Retient celle qui implémente {@link Observable} avec la valeur
 *       d'intérêt la plus élevée.</li>
 *   <li>Se déplace sur sa case et comptabilise l'observation.</li>
 * </ol>
 *
 * @see Bateau
 * @see Observable
 * @see Dauphine
 * @see Requin
 */
public class Touristique extends Bateau {

    /** Cumul total des individus observés depuis le début de la simulation. */
    private int totalObservations;

    /**
     * Construit un bateau touristique et le place sur le terrain.
     *
     * @param terrain terrain de la simulation
     * @param lig     ligne de départ (1-indexée)
     * @param col     colonne de départ (1-indexée)
     */
    public Touristique(Terrain terrain, int lig, int col) {
        super(terrain, lig, col);
        this.totalObservations = 0;
    }

    /**
     * Localise l'élément {@link Observable} le plus attractif et se déplace vers lui.
     *
     * <p>Si aucun élément observable n'est présent sur le terrain, le bateau
     * reste immobile et affiche un message.</p>
     */
    @Override
    public void agir() {
        Animal_marin cible = null;
        int maxInteret = -1;

        ArrayList<Ressource> ressources = terrain.lesRessources();
        for (Ressource r : ressources) {
            if (r instanceof Observable) {
                int interet = ((Observable) r).getInteret();
                if (interet > maxInteret) {
                    maxInteret = interet;
                    cible = (Animal_marin) r;
                }
            }
        }

        if (cible == null) {
            System.out.println(this + " : aucun animal a observer.");
            return;
        }

        seDeplacer(cible.getLigne(), cible.getColonne());

        totalObservations += cible.getQuantite();
        System.out.println(this + " observe " + cible.getQuantite()
                + " " + cible.type + "(s) en (" + cible.getLigne() + "," + cible.getColonne() + ").");
    }

    /**
     * Retourne le nombre total d'individus observés depuis le début de la simulation.
     *
     * @return cumul des observations (&gt;= 0)
     */
    public int getTotalObservations() { return totalObservations; }
}
