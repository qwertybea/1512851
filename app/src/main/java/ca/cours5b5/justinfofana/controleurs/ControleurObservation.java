package ca.cours5b5.justinfofana.controleurs;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.justinfofana.modeles.MParametres;
import ca.cours5b5.justinfofana.modeles.MParametresPartie;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.modeles.Modele;

public class ControleurObservation {

    private ControleurObservation(){}

    private static Map<Modele, ListenerObservateur> observations;

    static {
        observations = new HashMap<>();
    }


    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur) {

        Modele modele = ControleurModeles.getModele(nomModele);

        observations.put(modele, listenerObservateur);

        listenerObservateur.reagirNouveauModele(modele);

    }

    public static void lancerObservation(Modele modele) {

        final ListenerObservateur listenerObservateur = observations.get(modele);

        if (listenerObservateur != null) {

            listenerObservateur.reagirChangementAuModele(modele);

        }
    }

    public static void detruireObservation(Modele modele) {

        observations.remove(modele);

    }

}
