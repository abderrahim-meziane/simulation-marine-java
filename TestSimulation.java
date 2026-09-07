/**
 * Classe de test et de démonstration de la simulation marine.
 *
 * <p>Ce programme illustre deux aspects :</p>
 * <ul>
 *   <li><strong>Gestion des erreurs</strong> : plusieurs cas invalides sont testés
 *       pour montrer le déclenchement de {@link SimulationException} et
 *       {@link IllegalArgumentException}.</li>
 *   <li><strong>Simulations normales</strong> : trois scénarios différents
 *       (petite mer, grande mer, mer polluée) sont lancés et leurs résultats
 *       affichés.</li>
 * </ul>
 *
 * @see Simulation
 * @see SimulationException
 */
public class TestSimulation {

    /**
     * Point d'entrée du programme de test.
     *
     * <p>Lance d'abord les tests d'erreurs attendues, puis trois simulations
     * normales avec des paramètres variés.</p>
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {

        // -------------------------------------------------------
        // DEMO : cas d'erreurs détectées par les exceptions
        // -------------------------------------------------------
        System.out.println("=== Tests d'erreurs (exceptions) ===\n");

        // Cas 1 : terrain de taille nulle -> SimulationException (vérifiée)
        try {
            Simulation sErreur = new Simulation(0, 5, 1, 0, 0, 0, 0, 0, 0, 3);
            sErreur.lancer();
        } catch (SimulationException e) {
            System.out.println("[ERREUR ATTENDUE] " + e.getMessage());
        }

        // Cas 2 : nombre d'étapes négatif -> SimulationException
        try {
            Simulation sErreur = new Simulation(5, 5, 1, 0, 0, 0, 0, 0, 0, -1);
            sErreur.lancer();
        } catch (SimulationException e) {
            System.out.println("[ERREUR ATTENDUE] " + e.getMessage());
        }

        // Cas 3 : trop de ressources pour le terrain -> SimulationException
        try {
            // Terrain 2x2 = 4 cases, mais 10 ressources
            Simulation sErreur = new Simulation(2, 2, 5, 3, 2, 0, 0, 0, 0, 3);
            sErreur.lancer();
        } catch (SimulationException e) {
            System.out.println("[ERREUR ATTENDUE] " + e.getMessage());
        }

        // Cas 4 : capacité de pêche invalide -> IllegalArgumentException (non vérifiée)
        try {
            Terrain t = new Terrain(5, 5);
            Peche p = new Peche(t, 1, 1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERREUR ATTENDUE] " + e.getMessage());
        }

        // Cas 5 : bateau avec terrain null -> IllegalArgumentException
        try {
            Netoyeur n = new Netoyeur(null, 1, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERREUR ATTENDUE] " + e.getMessage());
        }

        System.out.println("\n=== Fin des tests d'erreurs ===\n\n");


        // -------------------------------------------------------
        // Simulations normales (avec gestion des exceptions)
        // -------------------------------------------------------

        // --- Simulation 1 : petite mer, peu d'agents ---
        System.out.println("########################################");
        System.out.println("#  SIMULATION 1 : petite mer          #");
        System.out.println("########################################");
        try {
            Simulation s1 = new Simulation(5, 8, 3, 2, 1, 2, 1, 1, 1, 5);
            s1.lancer();
        } catch (SimulationException e) {
            System.err.println("Impossible de lancer la simulation 1 : " + e.getMessage());
        }

        System.out.println("\n\n");

        // --- Simulation 2 : grande mer, beaucoup d'agents ---
        System.out.println("########################################");
        System.out.println("#  SIMULATION 2 : grande mer          #");
        System.out.println("########################################");
        try {
            Simulation s2 = new Simulation(10, 15, 6, 4, 3, 5, 2, 2, 2, 8);
            s2.lancer();
        } catch (SimulationException e) {
            System.err.println("Impossible de lancer la simulation 2 : " + e.getMessage());
        }

        System.out.println("\n\n");

        // --- Simulation 3 : mer polluée, focus nettoyage ---
        System.out.println("########################################");
        System.out.println("#  SIMULATION 3 : mer tres polluee    #");
        System.out.println("########################################");
        try {
            // Peu d'animaux, beaucoup de déchets, pas de pêche
            Simulation s3 = new Simulation(7, 10, 2, 3, 1, 8, 0, 1, 3, 6);
            s3.lancer();
        } catch (SimulationException e) {
            System.err.println("Impossible de lancer la simulation 3 : " + e.getMessage());
        }
    }
}
