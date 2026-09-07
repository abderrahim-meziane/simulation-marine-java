import java.util.ArrayList;

/**
 * Bateau de pêche : cherche les bancs de {@link Sardine} les plus proches
 * et réduit leur population à chaque étape.
 *
 * <p>À chaque appel de {@link #agir()}, le bateau :</p>
 * <ol>
 *   <li>Localise le banc de sardines le plus proche (distance euclidienne).</li>
 *   <li>Se déplace sur sa case.</li>
 *   <li>Pêche jusqu'à {@code capacite} sardines.</li>
 *   <li>Retire le banc du terrain s'il est épuisé.</li>
 * </ol>
 *
 * @see Bateau
 * @see Sardine
 */
public class Peche extends Bateau {

    /** Cumul total des sardines pêchées depuis le début de la simulation. */
    private int totalPeche;

    /** Nombre maximum de sardines pêchées par étape. */
    private int capacite;

    /**
     * Construit un bateau de pêche et le place sur le terrain.
     *
     * @param terrain  terrain de la simulation
     * @param lig      ligne de départ (1-indexée)
     * @param col      colonne de départ (1-indexée)
     * @param capacite quantité maximale pêchée par étape (doit être &gt; 0)
     * @throws IllegalArgumentException si {@code capacite} &lt;= 0
     */
    public Peche(Terrain terrain, int lig, int col, int capacite) {
        super(terrain, lig, col);
        if (capacite <= 0)
            throw new IllegalArgumentException("La capacité de pêche doit être > 0, reçu : " + capacite);
        this.capacite = capacite;
        this.totalPeche = 0;
    }

    /**
     * Localise la sardine la plus proche, se déplace vers elle et pêche.
     *
     * <p>Si aucune sardine n'est présente sur le terrain, le bateau reste
     * immobile et affiche un message.</p>
     */
    @Override
    public void agir() {
        Sardine cible = null;
        double minDist = Double.MAX_VALUE;

        ArrayList<Ressource> ressources = terrain.lesRessources();
        for (Ressource r : ressources) {
            if (r instanceof Sardine) {
                double d = distance(r.getLigne(), r.getColonne());
                if (d < minDist) {
                    minDist = d;
                    cible = (Sardine) r;
                }
            }
        }

        if (cible == null) {
            System.out.println(this + " : aucune Sardine a pecher.");
            return;
        }

        seDeplacer(cible.getLigne(), cible.getColonne());

        int qte = cible.getQuantite();
        int peche = Math.min(capacite, qte);
        cible.setQuantite(qte - peche);
        totalPeche += peche;
        System.out.println(this + " peche " + peche + " Sardine(s) (reste " + cible.getQuantite() + ").");

        if (cible.getQuantite() <= 0) {
            terrain.viderCase(cible.getLigne(), cible.getColonne());
            System.out.println("  -> Banc de Sardine epuise et retire du terrain.");
        }
    }

    /**
     * Retourne le nombre total de sardines pêchées depuis le début de la simulation.
     *
     * @return cumul des sardines pêchées (&gt;= 0)
     */
    public int getTotalPeche() { return totalPeche; }
}
