package ca.cours5b5.justinfofana.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.justinfofana.global.GConstantes;

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

    private List<VEntete> entetes;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.initialiser();
    }

    private void initialiser() {
        this.creerGrille(4,4);
    }

    void creerGrille(int hauteur, int largeur) {
        this.initialiserColonnes(largeur);
        this.ajouterEnTetes(largeur);
        this.ajouterCases(hauteur, largeur);
    }

    private void initialiserColonnes(int largeur) {
        this.colonnesDeCases = new ArrayList<>();

    }

    private void ajouterEnTetes(int largeur) {
        this.entetes = new ArrayList<>();
        VEntete entete;
        for (int colonne = 0; colonne < largeur; colonne++) {
            entete = new VEntete(this.getContext(), colonne);
            this.entetes.add(entete);
            this.addView(entete, this.getMiseEnPageEntete(colonne));
            Log.d("Atelier06", "entete : " + colonne);
        }
    }

    private LayoutParams getMiseEnPageEntete(int colonne) {
        Spec specRangee = GridLayout.spec(GConstantes.RANGE_ENTETE, GConstantes.ENTETE_POIDS_RANGE);
        Spec specColonne = GridLayout.spec(colonne, GConstantes.ENTETE_POIDS_COLONNE);
        LayoutParams params = new LayoutParams(specRangee, specColonne);
        //params.width = 0;
        //params.height = 0;
        //params.setGravity(Gravity.FILL);

        params.rightMargin = GConstantes.GRILLE_MARGIN;
        params.leftMargin = GConstantes.GRILLE_MARGIN;

        return params;
    }

    private void ajouterCases(int hauteur, int largeur) {
        for (int i = 0; i < hauteur; i++) {
            this.colonnesDeCases.add(new Colonne());
        }

        VCase vCase;
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                vCase = new VCase(this.getContext(), hauteur, largeur);
                this.colonnesDeCases.get(i).add(vCase);
                this.addView(vCase, this.getMiseEnPageCase(hauteur, largeur));
            }
        }
    }

    private LayoutParams getMiseEnPageCase(int rangee, int colonne) {
        Spec specRangee = GridLayout.spec(rangee, GConstantes.CASE_POIDS_RANGE);
        Spec specColonne = GridLayout.spec(colonne, GConstantes.CASE_POIDS_COLONNE);
        LayoutParams params = new LayoutParams(specRangee, specColonne);
        //params.width = 0;
        //params.height = 0;
        //params.setGravity(Gravity.FILL);

        params.rightMargin = GConstantes.GRILLE_MARGIN;
        params.leftMargin = GConstantes.GRILLE_MARGIN;

        return params;
    }

}
