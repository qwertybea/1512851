package ca.cours5b5.justinfofana.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.MessageFormat;

import ca.cours5b5.justinfofana.R;

public class AParametres extends Activite {

    static { Log.d("Atelier04", Activite.class.getSimpleName() + "::static"); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        setContentView(R.layout.activity_parametres);
    }
}
