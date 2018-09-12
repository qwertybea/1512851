package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.global.GConstantes;
import ca.cours5b5.justinfofana.modeles.MParametres;

public class VParametres extends Vue implements AdapterView.OnItemSelectedListener {

    static { Log.d("Atelier04", Vue.class.getSimpleName() + "::static"); }

    private ArrayAdapter<Integer> adapterHauteur;
    private ArrayAdapter<Integer> adapterLargeur;
    private ArrayAdapter<Integer> adapterPourGagner;
    private Spinner spinnerHauteur;
    private Spinner spinnerLargeur;
    private Spinner spinnerPourGagner;

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

        spinnerHauteur = this.findViewById(R.id.spinner_hauteur);
        spinnerLargeur = this.findViewById(R.id.spinner_largeur);
        spinnerPourGagner = this.findViewById(R.id.spinner_pourGagner);

        spinnerHauteur.setOnItemSelectedListener(this);
        spinnerLargeur.setOnItemSelectedListener(this);
        spinnerPourGagner.setOnItemSelectedListener(this);

        adapterHauteur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        adapterLargeur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        adapterPourGagner = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);

        spinnerHauteur.setAdapter(adapterHauteur);
        spinnerLargeur.setAdapter(adapterLargeur);
        spinnerPourGagner.setAdapter(adapterPourGagner);

        adapterHauteur.addAll(MParametres.instance.getChoixHauteur());
        adapterLargeur.addAll(MParametres.instance.getChoixLargeur());
        adapterPourGagner.addAll(MParametres.instance.getChoixPourGagner());

        positionSpinners();

    }

    private void positionSpinners() {
        spinnerHauteur.setSelection(adapterHauteur.getPosition(MParametres.instance.hauteur));
        spinnerLargeur.setSelection(adapterLargeur.getPosition(MParametres.instance.largeur));
        spinnerPourGagner.setSelection(adapterPourGagner.getPosition(MParametres.instance.pourGagner));
    }

    // TODO call setters instead
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        Integer leChoix = (Integer)parent.getAdapter().getItem(parent.getSelectedItemPosition());

        if(spinner.getId() == R.id.spinner_hauteur) {
            MParametres.instance.hauteur = leChoix;
        } else if(spinner.getId() == R.id.spinner_largeur) {
            MParametres.instance.largeur = leChoix;
        } else {
            MParametres.instance.pourGagner = leChoix;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
