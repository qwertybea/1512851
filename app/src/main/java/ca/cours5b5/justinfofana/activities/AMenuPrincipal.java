package ca.cours5b5.justinfofana.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.justinfofana.R;

public class AMenuPrincipal extends Activite {

    static { Log.d("Atelier04", Activite.class.getSimpleName() + "::static"); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);

        Button boutonParametres = this.findViewById(R.id.button_parametres);

        Button boutonPartie = this.findViewById(R.id.button_partie);

        // FIXME: make this better
        boutonParametres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntention = new Intent(AMenuPrincipal.this, AParametres.class);
                AMenuPrincipal.this.startActivity(monIntention);
            }
        });
        boutonPartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntention = new Intent(AMenuPrincipal.this, APartie.class);
                AMenuPrincipal.this.startActivity(monIntention);
            }
        });

    }
}
