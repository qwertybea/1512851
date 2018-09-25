package ca.cours5b5.justinfofana.controleurs;

import java.util.Map;
import java.util.Set;

import ca.cours5b5.justinfofana.controleurs.interfaces.Fournisseur;
import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.justinfofana.global.GCommande;

public class ControleurAction {

    private static Map<GCommande, Action> actions;
    private static Set<Action> fileAttenteExecution;

    static {

        /* TRUC: initialiser le Map actions comme suit:
         *          - pour chaque GCommande
         *               - insérer une action vide
         *
         * (l'avantage est qu'ensuite on a plus à tester si
         *  une GCommande est dans le Map... elles y sont toutes!)
         */


        /* BONUS: pour le Set fileAttenteExecution, il existe
         *        une implémentation de Set qui préserve l'ordre
         *        d'insertion... c'est celle-là qu'on veut!
         *
         * (double bonus: pourquoi?)
         */
    }


    public static Action demanderAction(GCommande commande) {return null;}


    public static void fournirAction(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur) {}
    /*
     * En plus d'enregistrer le fournisseur
     * On doit vérifier si l'ajout du fournisseur a rendu une action en file d'attente exécutable
     *
     */


    static void executerDesQuePossible(Action action) {}


    private static void executerActionsExecutables() {}
    /*
     * Avant d'exécuter l'action:
     *     - l'enlever de la file d'attente
     *
     * Après avoir éxécuté l'action:
     *     - lancer l'observation du fournisseur de cette action (si possible)
     *
     */

    static boolean siActionExecutable(Action action) {return false;}

    private static void lancerObservationSiApplicable(Action action) {}

    private static synchronized void executerMaintenant(Action action) {}
    /*
     * BONUS: à quoi sert le synchronized?
     *
     */


    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur) {}

    private static void ajouterActionEnFileDAttente(Action action) {}

}
