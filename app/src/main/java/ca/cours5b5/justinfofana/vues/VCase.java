package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import java.text.MessageFormat;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.global.GCouleur;

public class VCase extends AppCompatButton {
    public VCase(Context context) {
        super(context);
    }

    public VCase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VCase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VCase(Context context, int rangee, int colonne) {
        super(context);
        this.setText(MessageFormat.format("{0}, {1}", rangee, colonne));
        initialiser();
    }

    private void initialiser() {

        changerCouleurDeFond(R.color.VIDE);

    }

    private void changerCouleurDeFond(int idCouleur) {

        setBackgroundColor(getResources().getColor(idCouleur, null));

    }

    public void afficherJeton(GCouleur jeton) {

        switch (jeton){

            case ROUGE:

                changerCouleurDeFond(R.color.ROUGE);
                break;

            case JAUNE:

                changerCouleurDeFond(R.color.JAUNE);
                break;

            default:

                changerCouleurDeFond(R.color.ERREUR);
                break;
        }
    }

}
