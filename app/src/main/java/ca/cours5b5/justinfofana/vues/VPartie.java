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
import ca.cours5b5.justinfofana.modeles.MParametresPartie;
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

        initialiser();

        observerPartie();

    }

    private void initialiser() {

        grille = findViewById(R.id.GridLayout_partie);

    }

    private void observerPartie() {

        ControleurObservation.observerModele(MPartie.class.getSimpleName(),
                new ListenerObservateur() {
                    @Override
                    public void reagirNouveauModele(Modele modele) {

                        MPartie partie = getPartie(modele);

                        preparerAffichage(partie);

                        miseAJourGrille(partie);

                    }

                    @Override
                    public void reagirChangementAuModele(Modele modele) {

                        MPartie partie = getPartie(modele);

                        miseAJourGrille(partie);

                    }
                });
    }

    private void preparerAffichage(MPartie partie) {

        MParametresPartie parametresPartie = partie.getParametres();

        grille.creerGrille(parametresPartie.getHauteur(), parametresPartie.getLargeur());

    }

    private MPartie getPartie(Modele modele){

        try{

            return (MPartie) modele;

        }catch(ClassCastException e){

            throw new ErreurObservation(e);

        }

    }

    private void miseAJourGrille(MPartie partie){

        grille.afficherJetons(partie.getGrille());

    }

}
