package ca.cours5b5.justinfofana.controleurs;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.justinfofana.modeles.MParametres;
import ca.cours5b5.justinfofana.modeles.MParametresPartie;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations;

    // FIXME: c'est temporaire, on va écrire un contrôleur de modèles à l'atelier09
    public static MPartie partie;
    /*
     * L'attribut est private: la vue doit obtenir le modèle par l'observation
     */

    static {
        observations = new HashMap<>();
    }


    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur) {

        if (nomModele.equals(MParametres.class.getSimpleName())) {

            MParametres mParametres = MParametres.getInstance();

            observations.put(mParametres, listenerObservateur);

            ControleurObservation.lancerObservation(mParametres);

        } else if (nomModele.equals(MPartie.class.getSimpleName())) {

            partie = new MPartie(MParametresPartie.aPartirMParametres(MParametres.getInstance()));

            observations.put(partie, listenerObservateur);

            ControleurObservation.lancerObservation(partie);

        }

    }
    /* NEW
     * Enregistrer le listener dans le Map observations
     * Lancer l'observation une première fois quand on reçoit le listener
     *
     * Pour l'instant, utiliser le nom pour décider quel modèle utiliser
     *     - MParametres.instance ou ControleurObservation.partie
     *
     * À l'atelier09 on va écrire ControleurModeles pour gérer les modèles
     *
     * BONUS: pourquoi le modèle est identifié par son nom? (et pas son objet comme dans le Map?)
     *
     */
    /* OLD
     *   - on enregistre le listener dans le Map observations
     *   - on lance l'observation une première fois quand on reçoit le listener
     *
     *   Note: pour l'instant il y a un seul modèle, le nomModele est ignoré (FIXME atelier07!)
     *
     *   BONUS: pourquoi le modèle est identifié par son nom? (et pas son objet comme dans le Map?)
     *
     */

    public static void lancerObservation(Modele modele) {

        ListenerObservateur observateur = observations.get(modele);

        if (observateur != null) {

            observateur.reagirNouveauModele(modele);

        }
    }
    public static void reagirObservation(Modele modele) {

        ListenerObservateur observateur = observations.get(modele);

        if (observateur != null) {

            observateur.reagirChangementAuModele(modele);

        }
    }

    /*
     * sera appelé par le ControleurAction après une action!
     *
     */

}
