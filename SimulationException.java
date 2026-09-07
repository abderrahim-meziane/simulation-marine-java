/**
 * Exception personnalisée (checked) pour les erreurs de la simulation marine.
 *
 * <p>Lancée lorsque des paramètres invalides sont fournis à la {@link Simulation}
 * ou qu'une opération impossible est tentée (ex. trop de ressources pour la taille
 * du terrain, nombre d'étapes négatif, dimensions nulles).</p>
 *
 * <p>Étant une <em>checked exception</em> (elle hérite de {@link Exception} et non
 * de {@link RuntimeException}), le compilateur oblige l'appelant à la capturer
 * explicitement avec un {@code try/catch} ou à la propager avec {@code throws}.</p>
 *
 * <p>Exemple :</p>
 * <pre>
 *   try {
 *       Simulation s = new Simulation(0, 5, ...);
 *   } catch (SimulationException e) {
 *       System.err.println("Erreur : " + e.getMessage());
 *   }
 * </pre>
 *
 * @see Simulation
 */
public class SimulationException extends Exception {

    /**
     * Construit une {@code SimulationException} avec le message d'erreur donné.
     *
     * @param message description de l'erreur
     */
    public SimulationException(String message) {
        super(message);
    }

    /**
     * Construit une {@code SimulationException} avec un message et une cause.
     *
     * <p>Utile pour encapsuler une exception technique dans une exception
     * métier plus lisible.</p>
     *
     * @param message description de l'erreur
     * @param cause   exception d'origine
     */
    public SimulationException(String message, Throwable cause) {
        super(message, cause);
    }
}
