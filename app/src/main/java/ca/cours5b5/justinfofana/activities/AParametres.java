package ca.cours5b5.justinfofana.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.MessageFormat;
import java.util.Map;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.global.DebugTools;
import ca.cours5b5.justinfofana.modeles.MParametres;
import ca.cours5b5.justinfofana.serialisation.Jsonification;

public class AParametres extends Activite {

    static { Log.d("Atelier04", Activite.class.getSimpleName() + "::static"); }

    private String cle = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            atelier2();

            if (savedInstanceState != null) {
                this.restaurerParametres(savedInstanceState);
            }

            setContentView(R.layout.activity_parametres);
        } catch (Exception e) {
            Log.e("error05", e.getMessage());
            e.printStackTrace();
        }
    }

    private void restaurerParametres(Bundle savedInstanceState) {
        String json = savedInstanceState.getString(this.cle);
        Map<String, Object> objetJson = Jsonification.enObjetJson(json);
        MParametres.instance.aPartirObjetJson(objetJson);
        Log.d("Atelier05", this.getClass().getSimpleName() + "::restaurerParametres, " + "clé: " + cle);
        Log.d("Atelier05", this.getClass().getSimpleName() + "::restaurerParametres, " + "json:\n" + json);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        this.sauvegarderParametres(outState);
    }

    private void sauvegarderParametres(Bundle outState) {
        Map<String, Object> objetJson = MParametres.instance.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        outState.putString(this.cle, json);

        Log.d("Atelier05", this.getClass().getSimpleName() + "::sauvegarderParametres, " + "clé: " + cle);
        Log.d("Atelier05", this.getClass().getSimpleName() + "::sauvegarderParametres, " + "json:\n" + json);
    }


    private void atelier2() {
        String language;
        String orientation;
        try {
            language = this.getResources().getString(R.string.LANGUAGE);
        }catch (Exception e) {
            language = e.toString();
        }

        try {
            orientation = this.getResources().getBoolean(R.bool.is_portrait) ? "portrait" : "paysage";
            if (!this.getResources().getBoolean(R.bool.is_portrait) || !this.getResources().getBoolean(R.bool.is_landscape)) {
                orientation = "unknown orientation format";
            }
        }catch (Exception e) {
            orientation = e.toString();
        }

        String message = MessageFormat.format("{0}! ({1})", language, orientation);
        Log.d("MonEtiquette", message);
    }
}
