package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.justinfofana.global.GLog;

public class VJoueur extends android.support.v7.widget.AppCompatTextView {

    public VJoueur(Context context) {
        super(context);
    }

    public VJoueur(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VJoueur(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        GLog.vue();
    }

    public void changerCouleurDeFond(int idCouleur) {

        setBackgroundColor(getResources().getColor(idCouleur, null));

    }

}
