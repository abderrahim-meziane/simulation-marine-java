import java.util.ArrayList;
public final class SimulationUtils {
 
    /** Séparateur utilisé dans les logs. */
    public static final String SEPARATEUR = "----------------------------------------";
 
    /**
     * Constructeur privé : cette classe ne doit pas être instanciée.
     */
    private SimulationUtils() {
        throw new UnsupportedOperationException("SimulationUtils est une classe utilitaire non instanciable.");
    }
 
    /**
     * Compte le nombre total d'individus (somme des quantités) d'un type donné
     * dans une liste de ressources.
     *
     * @param ressources liste des ressources du terrain
     * @param type       type à rechercher (ex. "Sardine", "Requin")
     * @return somme des quantités pour ce type, ou 0 si aucun trouvé
     */
    public static int compterRessourcesParType(ArrayList<Ressource> ressources, String type) {
        int total = 0;
        for (Ressource r : ressources) {
            if (r.type.equals(type)) {
                total += r.getQuantite();
            }
        }
        return total;
    }
 
    /**
     * Compte le nombre de cases occupées par des déchets dans la liste.
     *
     * @param ressources liste des ressources du terrain
     * @return nombre de cases contenant un Dechet
     */
    public static int compterDechets(ArrayList<Ressource> ressources) {
        int count = 0;
        for (Ressource r : ressources) {
            if (r instanceof Dechet) count++;
        }
        return count;
    }
 
    /**
     * Compte le nombre de cases occupées par des animaux marins.
     *
     * @param ressources liste des ressources du terrain
     * @return nombre de cases contenant un Animal_marin
     */
    public static int compterAnimaux(ArrayList<Ressource> ressources) {
        int count = 0;
        for (Ressource r : ressources) {
            if (r instanceof Animal_marin) count++;
        }
        return count;
    }
 
    /**
     * Calcule la quantité totale de toutes les ressources présentes sur le terrain.
     *
     * @param ressources liste des ressources du terrain
     * @return somme de toutes les quantités
     */
    public static int quantiteTotale(ArrayList<Ressource> ressources) {
        int total = 0;
        for (Ressource r : ressources) {
            total += r.getQuantite();
        }
        return total;
    }
 
    /**
     * Affiche un bilan lisible de l'état des ressources sur le terrain.
     *
     * @param ressources liste des ressources à analyser
     */
    public static void afficherBilan(ArrayList<Ressource> ressources) {
        System.out.println(SEPARATEUR);
        System.out.println("  BILAN DES RESSOURCES");
        System.out.println(SEPARATEUR);
        System.out.println("  Sardines     : " + compterRessourcesParType(ressources, "Sardine"));
        System.out.println("  Dauphins     : " + compterRessourcesParType(ressources, "Dauphin"));
        System.out.println("  Requins      : " + compterRessourcesParType(ressources, "Requin"));
        System.out.println("  Dechets (cases) : " + compterDechets(ressources));
        System.out.println("  Total individus : " + quantiteTotale(ressources));
        System.out.println(SEPARATEUR);
    }
 
    /**
     * Formate un message de log avec un préfixe d'étape.
     *
     * @param numEtape numéro de l'étape
     * @param message  message à afficher
     * @return chaîne formatée
     */
    public static String formatLog(int numEtape, String message) {
        return "[Etape " + numEtape + "] " + message;
    }
}