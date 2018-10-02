package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.controleurs.ControleurObservation;
import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.modeles.Modele;

public class VPartie extends Vue {

    private VGrille grille;


    public VPartie(Context context) {
        super(context);
    }

    public VPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.initialiser();
    }

    private void initialiser() {
        this.observerPartie();
    }

    private void observerPartie() {
        ControleurObservation.observerModele("what am i?", new ListenerObservateur() {
            @Override
            public void reagirChangementAuModele(Modele modele) {

            }
        });
    }
    /*
     * Appeler observer pour obtenir le modèle
     *
     * Une fois le modèle obtenu, créer la grille d'affichage
     *
     */

    private MPartie getPartie(Modele modele) {return null;}

    private void initialiserGrille(MPartie partie) {}

}
