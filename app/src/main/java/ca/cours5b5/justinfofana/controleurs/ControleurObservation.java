package ca.cours5b5.justinfofana.controleurs;

import java.util.Map;

import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.justinfofana.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations;

    static {

    }


    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur)
    /*
     *   - on enregistre le listener dans le Map observations
     *   - on lance l'observation une première fois quand on reçoit le listener
     *
     *   Note: pour l'instant il y a un seul modèle, le nomModele est ignoré (FIXME atelier07!)
     *
     *   BONUS: pourquoi le modèle est identifié par son nom? (et pas son objet comme dans le Map?)
     *
     */

    public static void lancerObservation(Modele modele)
    /*
     * sera appelé par le ControleurAction après une action!
     *
     */

}
