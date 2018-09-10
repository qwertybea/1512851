package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.global.GConstantes;

public class VParametres extends Vue {

    static { Log.d("Atelier04", Vue.class.getSimpleName() + "::static"); }

    public VParametres(android.content.Context context) {
        super(context);
    }

    public VParametres(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    public VParametres(android.content.Context context, android.util.AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        Spinner spinnerHauteur = this.findViewById(R.id.spinner_hauteur);
        Spinner spinnerLargeur = this.findViewById(R.id.spinner_largeur);
        Spinner spinnerPourGagner = this.findViewById(R.id.spinner_pourGagner);

        ArrayAdapter<String> adapterHauteur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<String> adapterLargeur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<String> adapterPourGagner = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);

        spinnerHauteur.setAdapter(adapterHauteur);
        spinnerLargeur.setAdapter(adapterLargeur);
        spinnerPourGagner.setAdapter(adapterPourGagner);

        for (int i = GConstantes.HAUTEUR_MIN; i <= GConstantes.HAUTEUR_MAX; i++) {
            adapterHauteur.add(""+i);
        }

        for (int i = GConstantes.LARGEUR_MIN; i <= GConstantes.LARGEUR_MAX; i++) {
            adapterLargeur.add(""+i);
        }
        for (int i = GConstantes.SCORE_GAGNANT_MIN; i <= GConstantes.SCORE_GAGNANT_MAX; i++) {
            adapterPourGagner.add(""+i);
        }

        spinnerHauteur.setSelection(GConstantes.HAUTEUR_DEFAUT - GConstantes.HAUTEUR_MIN);
        spinnerLargeur.setSelection(GConstantes.LARGEUR_DEFAUT - GConstantes.LARGEUR_MIN);
        spinnerPourGagner.setSelection(GConstantes.SCORE_GAGNANT_DEFAUT - GConstantes.SCORE_GAGNANT_MIN);

    }
}
