package ca.cours5b5.justinfofana.controleurs;

import ca.cours5b5.justinfofana.global.GCouleur;

public class ControleurPartie {

    private ControleurPartie(){}

    private static final ControleurPartie instance = new ControleurPartie();

    public static ControleurPartie getInstance() {
        return instance;
    }

    public void gagnerPartie(GCouleur couleurGagnante){

    }
    /*
     * Utiliser une action pour afficher le message au gagnant
     *
    */

}
