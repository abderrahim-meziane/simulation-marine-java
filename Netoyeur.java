import java.util.ArrayList;

/**
 * Bateau nettoyeur : localise les {@link Dechet}s les plus proches et les
 * retire du terrain à chaque étape.
 *
 * <p>À chaque appel de {@link #agir()}, le bateau :</p>
 * <ol>
 *   <li>Localise le déchet le plus proche (distance euclidienne).</li>
 *   <li>Se déplace sur sa case.</li>
 *   <li>Retire intégralement le déchet du terrain.</li>
 * </ol>
 *
 * @see Bateau
 * @see Dechet
 */
public class Netoyeur extends Bateau {

    /** Cumul total des unités de déchets ramassées depuis le début de la simulation. */
    private int totalNettoye;

    /**
     * Construit un bateau nettoyeur et le place sur le terrain.
     *
     * @param terrain terrain de la simulation
     * @param lig     ligne de départ (1-indexée)
     * @param col     colonne de départ (1-indexée)
     */
    public Netoyeur(Terrain terrain, int lig, int col) {
        super(terrain, lig, col);
        this.totalNettoye = 0;
    }

    /**
     * Localise le déchet le plus proche, se déplace vers lui et le retire du terrain.
     *
     * <p>Si aucun déchet n'est présent sur le terrain, le bateau reste
     * immobile et affiche un message.</p>
     */
    @Override
    public void agir() {
        Dechet cible = null;
        double minDist = Double.MAX_VALUE;

        ArrayList<Ressource> ressources = terrain.lesRessources();
        for (Ressource r : ressources) {
            if (r instanceof Dechet) {
                double d = distance(r.getLigne(), r.getColonne());
                if (d < minDist) {
                    minDist = d;
                    cible = (Dechet) r;
                }
            }
        }

        if (cible == null) {
            System.out.println(this + " : aucun dechet a ramasser.");
            return;
        }

        seDeplacer(cible.getLigne(), cible.getColonne());

        int qte = cible.getQuantite();
        terrain.viderCase(cible.getLigne(), cible.getColonne());
        totalNettoye += qte;
        System.out.println(this + " ramasse " + qte + " unite(s) de " + cible.type + ".");
    }

    /**
     * Retourne le nombre total d'unités de déchets ramassées depuis le début
     * de la simulation.
     *
     * @return cumul des déchets ramassés (&gt;= 0)
     */
    public int getTotalNettoye() { return totalNettoye; }
}
