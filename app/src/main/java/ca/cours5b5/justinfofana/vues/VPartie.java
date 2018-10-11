package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.controleurs.ControleurObservation;
import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.justinfofana.exceptions.ErreurObservation;
import ca.cours5b5.justinfofana.global.DebugTools;
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

        this.grille = findViewById(R.id.GridLayout_partie);

        this.observerPartie();

    }

    private void observerPartie() {
        ControleurObservation.observerModele(MPartie.class.getSimpleName(), new ListenerObservateur() {

            @Override
            public void reagirNouveauModele(Modele modele) {

                MPartie mPartie = getPartie(modele);

                initialiserGrille(mPartie);

            }

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

    private MPartie getPartie(Modele modele) {
        try {
            return (MPartie) modele;
        } catch (ErreurObservation e) {
            throw new ErreurObservation("mauvais modele");
        }
    }

    private void initialiserGrille(MPartie partie) {

        this.grille.creerGrille(partie.getParametres().getHauteur(), partie.getParametres().getLargeur());

    }

}
