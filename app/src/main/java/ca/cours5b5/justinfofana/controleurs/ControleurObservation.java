package ca.cours5b5.justinfofana.controleurs;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerGetModele;
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


    public static void observerModele(final String nomModele, final ListenerObservateur listenerObservateur) {

        ControleurModeles.getModele(nomModele, new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {

                // TODO: not sure how we know wither to call 'changement' ou 'nouveau'

                if (observations.get(modele) == null) {

                    observations.put(modele, listenerObservateur);

                    // FIXME: should not be callable from here
                    // but then i dont know where we have to create the 'ListenerGetModele'
                    // it must be in ControleurModele for it to call 'chargerDonnees()'
                    ControleurModeles.chargerDonnees(modele, nomModele, this);

                    listenerObservateur.reagirNouveauModele(modele);

                } else {

                    listenerObservateur.reagirChangementAuModele(modele);

                }

            }
        });

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
