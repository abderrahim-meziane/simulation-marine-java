/**
 * Animal marin de type Requin.
 *
 * <p>La population de requins augmente lentement (+2 %) à chaque étape via
 * {@link #evoluer()}. Les requins chassent les sardines via {@link #chasser(Terrain)}
 * et peuvent être observés par un {@link Touristique} car ils implémentent
 * {@link Observable}.</p>
 *
 * @see Animal_marin
 * @see Observable
 * @see Touristique
 */
public class Requin extends Animal_marin implements Observable {

    /**
     * Construit un groupe de requins avec la population initiale donnée.
     *
     * @param quantite population initiale (doit être &gt;= 0)
     */
    public Requin(int quantite) {
        super("Requin", quantite);
    }

    /**
     * Fait évoluer la population de requins d'une étape (+2 %).
     *
     * <p>La quantité est mise à jour par {@code setQuantite()} et ne peut
     * pas descendre en dessous de zéro.</p>
     */
    @Override
    public void evoluer() {
        int nouvelle = (int)(getQuantite() * 1.02);
        setQuantite(Math.max(0, nouvelle));
    }

    /**
     * Les requins chassent le banc de sardines le plus proche sur le terrain.
     *
     * <p>La quantité consommée est égale à deux fois la population de requins,
     * dans la limite de la quantité disponible. Si le banc de sardines est
     * épuisé, il est retiré du terrain.</p>
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
        int mange = Math.min(getQuantite() * 2, cible.getQuantite());
        cible.setQuantite(cible.getQuantite() - mange);
        System.out.println("Requin(s) chassent " + mange + " Sardine(s).");
        if (cible.getQuantite() <= 0)
            terrain.viderCase(cible.getLigne(), cible.getColonne());
    }

    // --- Implémentation de l'interface Observable ---

    /**
     * Retourne une description textuelle de ce groupe de requins.
     *
     * @return chaîne de la forme {@code "Requin(s) : N individu(s)"}
     */
    @Override
    public String getDescription() {
        return "Requin(s) : " + getQuantite() + " individu(s)";
    }

    /**
     * Retourne la valeur d'intérêt touristique de ce groupe de requins.
     *
     * <p>Calculé comme {@code quantite × 5} : les requins ont un très fort
     * attrait touristique.</p>
     *
     * @return valeur d'intérêt (&gt;= 0)
     */
    @Override
    public int getInteret() {
        return getQuantite() * 5;
    }

    /**
     * Retourne une représentation textuelle de ce Requin.
     *
     * @return chaîne de la forme {@code Requin[...]}
     */
    @Override
    public String toString() {
        return "Requin[" + super.toString() + "]";
    }
}
