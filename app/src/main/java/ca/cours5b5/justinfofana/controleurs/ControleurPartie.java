package ca.cours5b5.justinfofana.controleurs;

import ca.cours5b5.justinfofana.global.GCommande;
import ca.cours5b5.justinfofana.global.GCouleur;

public class ControleurPartie {

    private ControleurPartie(){}

    private static final ControleurPartie instance = new ControleurPartie();

    public static ControleurPartie getInstance() {
        return instance;
    }

    public void gagnerPartie(GCouleur couleurGagnante){

        Action actionAffichageGagnat = ControleurAction.demanderAction(GCommande.AFFICHER_GAGNANT);

        actionAffichageGagnat.setArguments(couleurGagnante);

        actionAffichageGagnat.executerDesQuePossible();

    }
    /*
     * Utiliser une action pour afficher le message au gagnant
     *
    */

}
