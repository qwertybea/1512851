package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.justinfofana.controleurs.Action;
import ca.cours5b5.justinfofana.controleurs.ControleurAction;
import ca.cours5b5.justinfofana.global.GCommande;
import ca.cours5b5.justinfofana.global.GConstantes;
import ca.cours5b5.justinfofana.global.GCouleur;
import ca.cours5b5.justinfofana.modeles.MColonne;
import ca.cours5b5.justinfofana.modeles.MGrille;

public class VGrille extends GridLayout {

    public VGrille(Context context) {
        super(context);
    }

    public VGrille(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int nombreRangees;

    private class Colonne extends ArrayList<VCase> {}

    private List<Colonne> colonnesDeCases;

    private Action actionEntete;

    private List<VEntete> entetes;


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        initialiser();

        demanderActionEntete();

    }

    private void demanderActionEntete() {

        actionEntete = ControleurAction.demanderAction(GCommande.JOUER_COUP_ICI);

    }

    private void initialiser() {

        colonnesDeCases = new ArrayList<>();

        entetes = new ArrayList<>();

    }

    void creerGrille(int hauteur, int largeur) {

        // +1 pour la rangee des en-têtes
        this.nombreRangees = hauteur + 1;

        this.setRowCount(nombreRangees);
        this.setColumnCount(largeur);

        initialiserColonnes(largeur);

        ajouterEnTetes(largeur);
        ajouterCases(hauteur, largeur);

    }

    void recreerGrille(int hauteur, int largeur) {

        retirerAncienControl();

        initialiser();

        creerGrille(hauteur, largeur);

        // TODO: find out whether this stays in the next episode
        System.gc();

    }

    public void retirerAncienControl() {

        this.removeAllViewsInLayout();

    }

    private void initialiserColonnes(int largeur){

        for(int i=0; i<largeur; i++){

            colonnesDeCases.add(new Colonne());

        }
    }

    private void ajouterEnTetes(int largeur){

        for(int colonne=0; colonne<largeur; colonne++){

            VEntete entete = new VEntete(getContext(), colonne);

            LayoutParams miseEnPageEntete = getMiseEnPageEntete(colonne);

            this.addView(entete, miseEnPageEntete);

            entetes.add(entete);

            installerListenerEntete(entete);

        }
    }

    private LayoutParams getMiseEnPageEntete(int colonne){

        Spec specRangee = GridLayout.spec(GConstantes.RANGE_ENTETE, GConstantes.ENTETE_POIDS_RANGE);
        Spec specColonne = GridLayout.spec(colonne, GConstantes.ENTETE_POIDS_COLONNE);

        LayoutParams paramsEntete = new LayoutParams(specRangee, specColonne);

        paramsEntete.width = 0;
        paramsEntete.height = 0;
        paramsEntete.setGravity(Gravity.FILL);
        paramsEntete.rightMargin = GConstantes.GRILLE_MARGIN;
        paramsEntete.leftMargin = GConstantes.GRILLE_MARGIN;


        return paramsEntete;
    }

    private void installerListenerEntete(final VEntete entete) {

        entete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                actionEntete.setArguments(entete.getColonne());

                if (actionEntete.peutExecuter()) {

                    actionEntete.executerDesQuePossible();

                }

            }
        });

    }

    public void verouillerEntetes() {
        for (VEntete entete: entetes) {
            entete.setEnabled(false);
        }
    }

    public void reagirPermissionEntete(MGrille mGrille) {

        for (int i = 0; i < mGrille.getColonnes().size(); i++) {

            if (mGrille.getMaxCoupsSurCol() == mGrille.getNombreCoupsSurCol(i)) {

                entetes.get(i).setEnabled(false);

            }

        }

    }

    private void ajouterCases(int hauteur, int largeur) {

        for (int colonne = 0; colonne < largeur; colonne++) {
            for (int rangee = 0; rangee < hauteur; rangee++) {

                VCase vCase = new VCase(getContext(), rangee, colonne);
                LayoutParams miseEnPageCase = getMiseEnPageCase(rangee, colonne);

                this.addView(vCase, miseEnPageCase);

                colonnesDeCases.get(colonne).add(vCase);

            }
        }
    }

    private LayoutParams getMiseEnPageCase(int rangee, int colonne){

        // Pour nous, la rangée 0 est en bas
        int dernierIndiceRangee = nombreRangees -1;
        int indiceRangeeCetteCase = dernierIndiceRangee -rangee;

        Spec specRangee = GridLayout.spec(indiceRangeeCetteCase, GConstantes.CASE_POIDS_RANGE);
        Spec specColonne = GridLayout.spec(colonne, GConstantes.CASE_POIDS_COLONNE);

        LayoutParams paramsCase = new LayoutParams(specRangee, specColonne);

        paramsCase.width = 0;
        paramsCase.height = 0;
        paramsCase.setGravity(Gravity.FILL);
        paramsCase.leftMargin = GConstantes.GRILLE_MARGIN;
        paramsCase.rightMargin = GConstantes.GRILLE_MARGIN;
        paramsCase.topMargin = GConstantes.GRILLE_MARGIN;
        paramsCase.bottomMargin = GConstantes.GRILLE_MARGIN;

        return paramsCase;
    }

    void afficherJetons(MGrille grille){

        List<MColonne> colonnes = grille.getColonnes();

        for(int numeroColonne=0; numeroColonne < colonnes.size(); numeroColonne++){

            List<GCouleur> jetons = colonnes.get(numeroColonne).getJetons();

            for(int numeroRangee=0; numeroRangee < jetons.size(); numeroRangee++){

                GCouleur jeton = jetons.get(numeroRangee);

                afficherJeton(numeroColonne, numeroRangee, jeton);

            }
        }
    }

    private void afficherJeton(int colonne, int rangee, GCouleur jeton){

        colonnesDeCases.get(colonne).get(rangee).afficherJeton(jeton);

    }

}
