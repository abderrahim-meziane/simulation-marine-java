import java.util.ArrayList;
import java.util.Random;

/**
 * Moteur principal de la simulation marine.
 *
 * <p>La simulation gère un {@link Terrain} peuplé de {@link Ressource}s
 * ({@link Sardine}, {@link Dauphine}, {@link Requin}, {@link Dechet}) et
 * d'une flotte de {@link Bateau}x ({@link Peche}, {@link Touristique},
 * {@link Netoyeur}). À chaque étape :</p>
 * <ol>
 *   <li>Les bateaux réalisent leur action ({@link Bateau#agir()}).</li>
 *   <li>Les prédateurs chassent ({@link Requin#chasser}, {@link Dauphine#chasser}).</li>
 *   <li>Les animaux marins évoluent naturellement ({@link Animal_marin#evoluer()}).</li>
 * </ol>
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 *   Simulation s = new Simulation(10, 10, 5, 3, 2, 4, 2, 1, 1, 8);
 *   s.lancer();
 * </pre>
 *
 * @see Terrain
 * @see Bateau
 * @see Animal_marin
 * @see SimulationException
 */
public class Simulation {

    /** Terrain sur lequel se déroule la simulation. */
    private Terrain terrain;

    /** Liste de tous les bateaux participant à la simulation. */
    private ArrayList<Bateau> bateaux;

    /** Nombre total d'étapes à simuler. */
    private int nbEtapes;

    /** Générateur aléatoire pour le placement initial des ressources et bateaux. */
    private Random rand;

    /**
     * Construit et initialise une simulation avec les paramètres donnés.
     *
     * <p>Les ressources et les bateaux sont placés aléatoirement sur le terrain.
     * Le nombre total de ressources ne doit pas dépasser la capacité du terrain
     * ({@code nbLig × nbCol}).</p>
     *
     * @param nbLig       nombre de lignes du terrain (&gt; 0)
     * @param nbCol       nombre de colonnes du terrain (&gt; 0)
     * @param nbSardines  nombre de bancs de sardines à placer (&gt;= 0)
     * @param nbDauphins  nombre de groupes de dauphins à placer (&gt;= 0)
     * @param nbRequins   nombre de groupes de requins à placer (&gt;= 0)
     * @param nbDechets   nombre de cases de déchets à placer (&gt;= 0)
     * @param nbPeche     nombre de bateaux de pêche (&gt;= 0)
     * @param nbTourist   nombre de bateaux touristiques (&gt;= 0)
     * @param nbNetoyeur  nombre de bateaux nettoyeurs (&gt;= 0)
     * @param nbEtapes    nombre d'étapes à simuler (&gt; 0)
     * @throws SimulationException si les dimensions sont invalides, si le nombre
     *                             d'étapes est &lt;= 0, ou si trop de ressources
     *                             sont demandées pour la taille du terrain
     */
    public Simulation(int nbLig, int nbCol,
                      int nbSardines, int nbDauphins, int nbRequins, int nbDechets,
                      int nbPeche, int nbTourist, int nbNetoyeur,
                      int nbEtapes) throws SimulationException {
        if (nbLig <= 0 || nbCol <= 0)
            throw new SimulationException("Dimensions du terrain invalides : " + nbLig + "x" + nbCol);
        if (nbEtapes <= 0)
            throw new SimulationException("Le nombre d etapes doit etre > 0, recu : " + nbEtapes);
        int capaciteTerrain = nbLig * nbCol;
        int nbRessources = nbSardines + nbDauphins + nbRequins + nbDechets;
        if (nbRessources > capaciteTerrain)
            throw new SimulationException("Trop de ressources (" + nbRessources
                    + ") pour un terrain de " + capaciteTerrain + " cases.");

        this.terrain = new Terrain(nbLig, nbCol);
        this.bateaux = new ArrayList<>();
        this.nbEtapes = nbEtapes;
        this.rand = new Random();

        initialiserRessources(nbSardines, nbDauphins, nbRequins, nbDechets);
        initialiserBateaux(nbPeche, nbTourist, nbNetoyeur);
    }

    /**
     * Place les ressources animales et les déchets aléatoirement sur le terrain.
     *
     * @param nbSardines nombre de bancs de sardines
     * @param nbDauphins nombre de groupes de dauphins
     * @param nbRequins  nombre de groupes de requins
     * @param nbDechets  nombre de cases de déchets
     */
    private void initialiserRessources(int nbSardines, int nbDauphins, int nbRequins, int nbDechets) {
        for (int i = 0; i < nbSardines; i++)
            placerRessource(new Sardine(rand.nextInt(20) + 10));
        for (int i = 0; i < nbDauphins; i++)
            placerRessource(new Dauphine(rand.nextInt(5) + 2));
        for (int i = 0; i < nbRequins; i++)
            placerRessource(new Requin(rand.nextInt(3) + 1));
        for (int i = 0; i < nbDechets; i++)
            placerRessource(new Dechet("Plastique", rand.nextInt(10) + 1));
    }

    /**
     * Cherche une case vide et y place la ressource donnée.
     *
     * <p>La recherche est limitée à 100 tentatives pour éviter une boucle infinie
     * sur un terrain presque plein.</p>
     *
     * @param r ressource à placer sur le terrain
     */
    private void placerRessource(Ressource r) {
        int tentatives = 0;
        int lig, col;
        do {
            lig = rand.nextInt(terrain.nbLignes) + 1;
            col = rand.nextInt(terrain.nbColonnes) + 1;
            tentatives++;
        } while (!terrain.caseEstVide(lig, col) && tentatives < 100);
        if (terrain.caseEstVide(lig, col))
            terrain.setCase(lig, col, r);
    }

    /**
     * Crée et place les bateaux à des positions aléatoires sur le terrain.
     *
     * @param nbPeche    nombre de bateaux de pêche
     * @param nbTourist  nombre de bateaux touristiques
     * @param nbNetoyeur nombre de bateaux nettoyeurs
     */
    private void initialiserBateaux(int nbPeche, int nbTourist, int nbNetoyeur) {
        for (int i = 0; i < nbPeche; i++) {
            int lig = rand.nextInt(terrain.nbLignes) + 1;
            int col = rand.nextInt(terrain.nbColonnes) + 1;
            bateaux.add(new Peche(terrain, lig, col, 5));
        }
        for (int i = 0; i < nbTourist; i++) {
            int lig = rand.nextInt(terrain.nbLignes) + 1;
            int col = rand.nextInt(terrain.nbColonnes) + 1;
            bateaux.add(new Touristique(terrain, lig, col));
        }
        for (int i = 0; i < nbNetoyeur; i++) {
            int lig = rand.nextInt(terrain.nbLignes) + 1;
            int col = rand.nextInt(terrain.nbColonnes) + 1;
            bateaux.add(new Netoyeur(terrain, lig, col));
        }
    }

    /**
     * Exécute une étape de la simulation.
     *
     * <p>L'ordre d'exécution est : actions des bateaux → chasse des prédateurs
     * → évolution naturelle des animaux → affichage du terrain.</p>
     *
     * @param numEtape numéro de l'étape courante (affiché dans les logs)
     */
    public void etape(int numEtape) {
        System.out.println("\n=== Etape " + numEtape + " ===");

        for (Bateau b : bateaux)
            b.agir();

        System.out.println("-- Phase de chasse --");
        for (Ressource r : terrain.lesRessources()) {
            if (r instanceof Requin)
                ((Requin) r).chasser(terrain);
        }
        for (Ressource r : terrain.lesRessources()) {
            if (r instanceof Dauphine)
                ((Dauphine) r).chasser(terrain);
        }

        for (Ressource r : terrain.lesRessources()) {
            if (r instanceof Animal_marin)
                ((Animal_marin) r).evoluer();
        }

        terrain.afficher(6);
        System.out.println(terrain);
    }

    /**
     * Lance la simulation complète pour le nombre d'étapes configuré.
     *
     * <p>Affiche l'état initial du terrain, exécute chaque étape via
     * {@link #etape(int)}, puis affiche les statistiques finales.</p>
     */
    public void lancer() {
        System.out.println("=== Debut de la simulation ===");
        terrain.afficher(6);
        System.out.println(terrain);
        for (int i = 1; i <= nbEtapes; i++)
            etape(i);
        afficherStatistiques();
    }

    /**
     * Affiche le bilan final de la simulation : sardines pêchées, observations
     * touristiques, déchets ramassés et ressources restantes sur le terrain.
     */
    private void afficherStatistiques() {
        System.out.println("\n=== Statistiques finales ===");
        int totalPeche = 0, totalObs = 0, totalNet = 0;
        for (Bateau b : bateaux) {
            if (b instanceof Peche)       totalPeche += ((Peche) b).getTotalPeche();
            if (b instanceof Touristique) totalObs   += ((Touristique) b).getTotalObservations();
            if (b instanceof Netoyeur)    totalNet   += ((Netoyeur) b).getTotalNettoye();
        }
        System.out.println("Total sardines pechees   : " + totalPeche);
        System.out.println("Total observations       : " + totalObs);
        System.out.println("Total dechets ramasses   : " + totalNet);
        System.out.println("Ressources restantes     : " + terrain.compterRessources());
        SimulationUtils.afficherBilan(terrain.lesRessources());
    }
}
