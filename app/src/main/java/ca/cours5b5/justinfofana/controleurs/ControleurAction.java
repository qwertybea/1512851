package ca.cours5b5.justinfofana.controleurs;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import ca.cours5b5.justinfofana.controleurs.interfaces.Fournisseur;
import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.justinfofana.global.DebugTools;
import ca.cours5b5.justinfofana.global.GCommande;
import ca.cours5b5.justinfofana.modeles.Modele;

public class ControleurAction {

    private static Map<GCommande, Action> actions;
    private static Set<Action> fileAttenteExecution;

    static {

        actions = new HashMap<>();
        for (GCommande commande : GCommande.values()) {
            actions.put(commande, new Action());
        }

        fileAttenteExecution = new LinkedHashSet<>();

    }

    public static Action demanderAction(GCommande commande) {
        return actions.get(commande);
    }

    public static void fournirAction(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur) {
        enregistrerFournisseur(fournisseur, commande, listenerFournisseur);

        executerActionsExecutables();
    }

    static void executerDesQuePossible(Action action) {
        ajouterActionEnFileDAttente(action);
        executerActionsExecutables();
    }

    private static void executerActionsExecutables() {

        for (Action action : fileAttenteExecution) {
            if(siActionExecutable(action)) {
                fileAttenteExecution.remove(action);
                executerMaintenant(action);
                lancerObservationSiApplicable(action);
            }
        }
    }

    static boolean siActionExecutable(Action action) {
        return action.listenerFournisseur != null;
    }

    private static void lancerObservationSiApplicable(Action action) {
        if (action.fournisseur instanceof Modele) {
//            FIXME: maybe i went wrong with my names somewhere
//            ControleurObservation.lancerObservation((Modele) action.fournisseur);
            ControleurObservation.reagirObservation((Modele) action.fournisseur);
        }
    }

    private static synchronized void executerMaintenant(Action action) {
        action.listenerFournisseur.executer(action.args);
    }

    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur) {
        Action action = actions.get(commande);
        action.fournisseur = fournisseur;
        action.listenerFournisseur = listenerFournisseur;
    }

    private static void ajouterActionEnFileDAttente(Action action) {
        fileAttenteExecution.add(action.cloner());
    }

}
