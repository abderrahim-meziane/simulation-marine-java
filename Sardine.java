/**
 * Animal marin de type Sardine (petite proie).
 *
 * <p>La population de sardines augmente rapidement (+10 %) à chaque étape
 * de simulation via {@link #evoluer()}. Les sardines constituent la principale
 * source de nourriture pour les {@link Requin}s et les {@link Dauphine}s,
 * et la cible des bateaux de {@link Peche}.</p>
 *
 * <p>Les sardines n'implémentent pas {@link Observable} : elles ne présentent
 * pas d'intérêt touristique.</p>
 *
 * @see Animal_marin
 * @see Peche
 * @see Requin
 * @see Dauphine
 */
public class Sardine extends Animal_marin {

    /**
     * Construit un banc de sardines avec la population initiale donnée.
     *
     * @param quantite population initiale (doit être &gt;= 0)
     */
    public Sardine(int quantite) {
        super("Sardine", quantite);
    }

    /**
     * Fait évoluer la population de sardines d'une étape (+10 %).
     *
     * <p>La quantité est mise à jour par {@code setQuantite()} et ne peut
     * pas descendre en dessous de zéro.</p>
     */
    @Override
    public void evoluer() {
        int nouvelle = (int)(getQuantite() * 1.10);
        setQuantite(Math.max(0, nouvelle));
    }

    /**
     * Retourne une représentation textuelle de ce banc de sardines.
     *
     * @return chaîne de la forme {@code Sardine[...]}
     */
    @Override
    public String toString() {
        return "Sardine[" + super.toString() + "]";
    }
}
