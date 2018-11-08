package ca.cours5b5.justinfofana.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.controleurs.ControleurModeles;
import ca.cours5b5.justinfofana.controleurs.ControleurObservation;
import ca.cours5b5.justinfofana.donnees.SauvegardeTemporaire;
import ca.cours5b5.justinfofana.modeles.MParametres;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.serialisation.Jsonification;
import ca.cours5b5.justinfofana.vues.VPartie;

public class APartie extends Activite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_partie);

    }

    @Override
    protected void onPause() {
        super.onPause();

        ControleurModeles.sauvegarderModele(MPartie.class.getSimpleName());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // FIXME: perhaps it should
        //ControleurModeles.sauvegarderModeleDansCetteSource(MPartie.class.getSimpleName(),
        //        new SauvegardeTemporaire(outState));

    }

}
