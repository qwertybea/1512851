package ca.cours5b5.justinfofana.activities;

import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.controleurs.ControleurModeles;
import ca.cours5b5.justinfofana.modeles.MParametres;

public class AParametres extends Activite {

    static { Log.d("Atelier04", Activite.class.getSimpleName() + "::static"); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

    }

    @Override
    protected void onPause() {
        super.onPause();

        ControleurModeles.sauvegarderModele(MParametres.class.getSimpleName());

    }
}
