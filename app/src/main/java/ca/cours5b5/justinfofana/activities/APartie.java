package ca.cours5b5.justinfofana.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.controleurs.ControleurObservation;
import ca.cours5b5.justinfofana.modeles.MParametres;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.serialisation.Jsonification;
import ca.cours5b5.justinfofana.vues.VPartie;

public class APartie extends Activite {

    private String cle = MPartie.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        setContentView(R.layout.activity_partie);

        if (savedInstanceState != null) {
            this.restaurerPartie(savedInstanceState);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        this.sauvegarderPartie(outState);
    }

    private void restaurerPartie(Bundle savedInstanceState) {
        String json = savedInstanceState.getString(this.cle);
        Map<String, Object> objetJson = Jsonification.enObjetJson(json);
        ControleurObservation.partie.aPartirObjetJson(objetJson);
        Log.d("Atelier05", this.getClass().getSimpleName() + "::restaurerPartie, clé: " + cle);
        Log.d("Atelier05", this.getClass().getSimpleName() + "::restaurerPartie, json:\n" + json);
    }

    private void sauvegarderPartie(Bundle outState) {
        Map<String, Object> objetJson = ControleurObservation.partie.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        outState.putString(this.cle, json);

        Log.d("Atelier08", this.getClass().getSimpleName() + "::sauvegarderPartie, clé: " + cle);
        Log.d("Atelier08", this.getClass().getSimpleName() + "::sauvegarderPartie, json:\n" + json);
    }


}
