package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.justinfofana.modeles.MPartieReseau;

public class VPartieReseau extends VPartie {

    public VPartieReseau(Context context) {
        super(context);
    }

    public VPartieReseau(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartieReseau(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected String getNomModele(){
        return MPartieReseau.class.getSimpleName();
    }

}
