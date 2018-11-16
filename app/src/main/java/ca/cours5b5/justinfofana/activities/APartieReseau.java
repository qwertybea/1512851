package ca.cours5b5.justinfofana.activities;

import android.os.Bundle;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.controleurs.ControleurModeles;
import ca.cours5b5.justinfofana.controleurs.ControleurPartieReseau;
import ca.cours5b5.justinfofana.controleurs.interfaces.Fournisseur;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.modeles.MPartieReseau;

public class APartieReseau extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_partie_reseau);

    }

    @Override
    protected void onPause() {
        super.onPause();

        ControleurPartieReseau.getInstance().detruireSauvegardeServeur();
        ControleurPartieReseau.getInstance().deconnecterDuServeur();

    }
    /*
     * Avec ControleurPartieReseau, détruire la partie sur le serveur
     * Déconnecter ControleurPartieReseau du serveur
     */

    @Override
    protected void onResume() {
        super.onResume();

        ControleurPartieReseau.getInstance().connecterAuServeur();

    }
    /*
     * Connecter le ControleurPartieReseau au serveur
     *
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ControleurModeles.effacerModele(MPartieReseau.class.getSimpleName());

    }
    /*
     * BONUS: avec ControleurModeles, détruire le modèle MPartieReseau
     *
     */

}
