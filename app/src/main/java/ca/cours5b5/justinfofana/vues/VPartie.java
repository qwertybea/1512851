package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.justinfofana.R;
import ca.cours5b5.justinfofana.controleurs.ControleurObservation;
import ca.cours5b5.justinfofana.controleurs.interfaces.Fournisseur;
import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.justinfofana.exceptions.ErreurObservation;
import ca.cours5b5.justinfofana.global.GCouleur;
import ca.cours5b5.justinfofana.global.GLog;
import ca.cours5b5.justinfofana.modeles.MGrille;
import ca.cours5b5.justinfofana.modeles.MParametresPartie;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.modeles.Modele;

public class VPartie extends Vue implements Fournisseur {

    private VGrille grille;

    private List<VJoueur> joueurs;

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

        initialiserJoueurs();

        observerPartie();

    }

    private void initialiser() {

        grille = findViewById(R.id.GridLayout_partie);

        joueurs = new ArrayList<>();

    }

    private void initialiserJoueurs() {

        // we should ask or receive how many players there are from the modele
        // then we create new playerTexts and add them to 'joueurs'

        VJoueur joueur1 = findViewById(R.id.texte_joueur_un);
        VJoueur joueur2 = findViewById(R.id.texte_joueur_deux);

        joueurs.add(joueur1);
        joueurs.add(joueur2);

        for (int i = 0; i < joueurs.size(); i++) {
            GLog.vue(joueurs.get(i));
        }

    }

    private void observerPartie() {

        ControleurObservation.observerModele(getNomModele(),
                new ListenerObservateur() {
                    @Override
                    public void reagirNouveauModele(Modele modele) {

                        GLog.activite(modele);

                        MPartie partie = getPartie(modele);

                        preparerAffichage(partie);

                        reagirChangementAuModele(modele);

                    }

                    @Override
                    public void reagirChangementAuModele(Modele modele) {

                        GLog.activite(modele);

                        MPartie partie = getPartie(modele);

                        miseAJourGrille(partie);

                        miseAJourCouleurJoueurs(partie);

                    }
                });
    }

    protected String getNomModele() {
        return MPartie.class.getSimpleName();
    }

    private void preparerAffichage(MPartie partie) {

        MParametresPartie parametresPartie = partie.getParametres();

        grille.creerGrille(parametresPartie.getHauteur(), parametresPartie.getLargeur());

    }

    private void reinitialiserAffichage(MPartie partie) {

        MParametresPartie parametresPartie = partie.getParametres();

        grille.recreerGrille(parametresPartie.getHauteur(), parametresPartie.getLargeur());

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

        grille.reagirPermissionEntete(partie.getGrille());

        verifierPartitFini(partie);

    }

    public void verifierPartitFini(MPartie mPartie) {

        MGrille mGrille = mPartie.getGrille();

        int colonnesJouables = mGrille.getColonnes().size();

        for (int i = 0; i < mGrille.getColonnes().size(); i++) {

            if (mGrille.getMaxCoupsSurCol() == mGrille.getNombreCoupsSurCol(i)) {

                colonnesJouables--;

            }
        }

        if (colonnesJouables == 0) {
            reagirPartieFini(mPartie);
        }

    }

    public void reagirPartieFini(MPartie mPartie) {

        mPartie.recommencerPartie();

        reinitialiserAffichage(mPartie);

        miseAJourGrille(mPartie);

        miseAJourCouleurJoueurs(mPartie);

    }

    private void miseAJourCouleurJoueurs(MPartie partie){

        changerCouleurJoueur(partie.getJoueurCourant(), partie.getCouleurCourante());

    }

    private void changerCouleurJoueur(int joueur, GCouleur gCouleur) {

        int couleur = gCouleurACouleur(gCouleur);
        int couleurVide = R.color.TRANSPARANT;

        for (VJoueur j : joueurs) {
            j.changerCouleurDeFond(couleurVide);
        }

        GLog.activite(joueur, couleur);
        joueurs.get(joueur).changerCouleurDeFond(couleur);


        // track player count and turn in modele
        // put all players in an array and find relevant one using 'joueur'
        // change its color

    }

    // mettre une methode qui fait ceci quelque part d'util pour tout le programme
    private int gCouleurACouleur(GCouleur gCouleur) {
        int couleur;
        switch (gCouleur){

            case ROUGE:

                couleur = R.color.ROUGE;
                break;

            case JAUNE:

                couleur = R.color.JAUNE;
                break;

            default:

                couleur = R.color.ERREUR;
                break;
        }

        return couleur;
    }

}
