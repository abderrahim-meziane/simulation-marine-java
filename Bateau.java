/**
 * Classe abstraite représentant un bateau (agent) se déplaçant sur le terrain marin.
 *
 * <p>Un bateau possède une position (ligne, colonne) sur le {@link Terrain} et réalise
 * une action à chaque étape de la simulation via {@link #agir()}. Contrairement aux
 * {@link Ressource}s, les bateaux ne sont pas stockés sur les cases du terrain.</p>
 *
 * <p>Sous-classes concrètes : {@link Peche}, {@link Touristique}, {@link Netoyeur}.</p>
 *
 * @see Peche
 * @see Touristique
 * @see Netoyeur
 */
public abstract class Bateau {

    /** Terrain sur lequel ce bateau évolue. */
    protected Terrain terrain;

    /** Ligne courante du bateau (1-indexée). */
    protected int lig;

    /** Colonne courante du bateau (1-indexée). */
    protected int col;

    /**
     * Construit un bateau et le place à la position donnée sur le terrain.
     *
     * @param terrain terrain de la simulation ; ne doit pas être {@code null}
     * @param lig     ligne de départ (entre 1 et {@code terrain.nbLignes})
     * @param col     colonne de départ (entre 1 et {@code terrain.nbColonnes})
     * @throws IllegalArgumentException si {@code terrain} est {@code null}
     *                                  ou si les coordonnées sont hors limites
     */
    public Bateau(Terrain terrain, int lig, int col) {
        if (terrain == null)
            throw new IllegalArgumentException("Le terrain ne peut pas être null.");
        if (lig < 1 || lig > terrain.nbLignes)
            throw new IllegalArgumentException("Ligne invalide : " + lig + " (terrain a " + terrain.nbLignes + " lignes)");
        if (col < 1 || col > terrain.nbColonnes)
            throw new IllegalArgumentException("Colonne invalide : " + col + " (terrain a " + terrain.nbColonnes + " colonnes)");
        this.terrain = terrain;
        this.lig = lig;
        this.col = col;
    }

    /**
     * Calcule la distance euclidienne entre ce bateau et la case {@code (lig, col)}.
     *
     * @param lig ligne de la case cible
     * @param col colonne de la case cible
     * @return distance euclidienne (toujours &gt;= 0)
     */
    public double distance(int lig, int col) {
        int dl = this.lig - lig;
        int dc = this.col - col;
        return Math.sqrt(dl * dl + dc * dc);
    }

    /**
     * Déplace ce bateau vers la case {@code (lig, col)}.
     *
     * @param lig nouvelle ligne
     * @param col nouvelle colonne
     */
    public void seDeplacer(int lig, int col) {
        this.lig = lig;
        this.col = col;
    }

    /**
     * Réalise l'action propre à ce bateau pour une étape de simulation.
     *
     * <p>Chaque sous-classe définit son comportement : pêche, observation,
     * nettoyage, etc. Cette méthode est appelée par {@link Simulation#etape(int)}.</p>
     */
    public abstract void agir();

    /**
     * Retourne la ligne courante de ce bateau.
     *
     * @return ligne (1-indexée)
     */
    public int getLig() { return lig; }

    /**
     * Retourne la colonne courante de ce bateau.
     *
     * @return colonne (1-indexée)
     */
    public int getCol() { return col; }

    /**
     * Retourne une représentation textuelle du bateau avec sa position.
     *
     * @return chaîne de la forme {@code NomClasse en (lig,col)}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " en (" + lig + "," + col + ")";
    }
}
