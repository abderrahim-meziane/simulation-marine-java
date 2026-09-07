/**
 * Animal marin de type Dauphin.
 *
 * <p>La population de dauphins augmente modérément (+5 %) à chaque étape
 * de simulation via {@link #evoluer()}. Les dauphins chassent également
 * les sardines via {@link #chasser(Terrain)} et peuvent être observés par
 * un {@link Touristique} car ils implémentent l'interface {@link Observable}.</p>
 *
 * @see Animal_marin
 * @see Observable
 * @see Touristique
 */
public class Dauphine extends Animal_marin implements Observable {

    /**
     * Construit un groupe de dauphins avec la population initiale donnée.
     *
     * @param quantite population initiale (doit être &gt;= 0)
     */
    public Dauphine(int quantite) {
        super("Dauphin", quantite);
    }

    /**
     * Constructeur par copie : crée un {@code Dauphine} avec la même population
     * que l'original.
     *
     * @param autre le {@code Dauphine} à copier ; ne doit pas être {@code null}
     * @throws IllegalArgumentException si {@code autre} est {@code null}
     */
    public Dauphine(Dauphine autre) {
        super("Dauphin", autre != null ? autre.getQuantite() : 0);
        if (autre == null)
            throw new IllegalArgumentException("Le Dauphine à copier ne peut pas être null.");
    }

    /**
     * Fait évoluer la population de dauphins d'une étape (+5 %).
     *
     * <p>La quantité est mise à jour par {@code setQuantite()} et ne peut
     * pas descendre en dessous de zéro.</p>
     */
    @Override
    public void evoluer() {
        int nouvelle = (int)(getQuantite() * 1.05);
        setQuantite(Math.max(0, nouvelle));
    }

    /**
     * Les dauphins chassent le banc de sardines le plus proche sur le terrain.
     *
     * <p>La quantité consommée est égale à la population de dauphins, dans la
     * limite de la quantité disponible. Si le banc de sardines est épuisé,
     * il est retiré du terrain.</p>
     *
     * @param terrain terrain de la simulation contenant les ressources
     */
    public void chasser(Terrain terrain) {
        Sardine cible = null;
        double minDist = Double.MAX_VALUE;
        for (Ressource r : terrain.lesRessources()) {
            if (r instanceof Sardine) {
                int dl = getLigne() - r.getLigne();
                int dc = getColonne() - r.getColonne();
                double d = Math.sqrt(dl * dl + dc * dc);
                if (d < minDist) { minDist = d; cible = (Sardine) r; }
            }
        }
        if (cible == null) return;
        int mange = Math.min(getQuantite(), cible.getQuantite());
        cible.setQuantite(cible.getQuantite() - mange);
        System.out.println("Dauphin(s) chassent " + mange + " Sardine(s).");
        if (cible.getQuantite() <= 0)
            terrain.viderCase(cible.getLigne(), cible.getColonne());
    }

    // --- Implémentation de l'interface Observable ---

    /**
     * Retourne une description textuelle de ce groupe de dauphins.
     *
     * @return chaîne de la forme {@code "Dauphin(s) : N individu(s)"}
     */
    @Override
    public String getDescription() {
        return "Dauphin(s) : " + getQuantite() + " individu(s)";
    }

    /**
     * Retourne la valeur d'intérêt touristique de ce groupe de dauphins.
     *
     * <p>Calculé comme {@code quantite × 3} : les dauphins ont un fort
     * attrait touristique.</p>
     *
     * @return valeur d'intérêt (&gt;= 0)
     */
    @Override
    public int getInteret() {
        return getQuantite() * 3;
    }

    /**
     * Retourne une représentation textuelle de ce Dauphine.
     *
     * @return chaîne de la forme {@code Dauphine[...]}
     */
    @Override
    public String toString() {
        return "Dauphine[" + super.toString() + "]";
    }
}
