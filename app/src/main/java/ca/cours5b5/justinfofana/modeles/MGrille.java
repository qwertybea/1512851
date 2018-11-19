package ca.cours5b5.justinfofana.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.exceptions.ErreurSerialisation;
import ca.cours5b5.justinfofana.global.GCouleur;
import ca.cours5b5.justinfofana.global.GDirection;

public class MGrille extends Modele {

    private List<MColonne> colonnes;

    private int max_coups_sur_col;

    public MGrille(int hauteur, int largeur){

        colonnes = new ArrayList<>();

        initialiserColonnes(largeur);

        max_coups_sur_col = hauteur;

    }

    private void initialiserColonnes(int largeur) {

        for(int i=0; i<largeur; i++){

            colonnes.add(new MColonne());

        }
    }


    public List<MColonne> getColonnes() {
        return colonnes;
    }

    public int getMaxCoupsSurCol() {
        return max_coups_sur_col;
    }

    public int getNombreCoupsSurCol(int colonne) {

        return getColonnes().get(colonne).getJetons().size();

    }

    public void placerJeton(int colonne, GCouleur couleur) {

        colonnes.get(colonne).placerJeton(couleur);

    }

    public boolean siCouleurGagne(GCouleur couleur, int pourGagner){

        for (int idColonne = 0; idColonne < getColonnes().size(); idColonne++) {

            if (siCouleurGagneCetteColonne(couleur, idColonne, pourGagner)) {
                return true;
            }

        }

        return false;
    }

    private boolean siCouleurGagneCetteColonne(GCouleur couleur, int idColonne, int pourGagner){

        for (int idRangee = 0; idRangee < getColonnes().get(idColonne).getJetons().size(); idRangee++) {

            if (siCouleurGagneCetteCase(couleur, idColonne, idRangee, pourGagner)) {
                return true;
            }

        }

        return false;
    }

    private boolean siCouleurGagneCetteCase(GCouleur couleur, int idColonne, int idRangee, int pourGagner) {
        for (GDirection direction : GDirection.directions) {
            if (siCouleurGagneDansCetteDirection(couleur, idColonne, idRangee, direction, pourGagner)) {
                return true;
            }
        }

        return false;
    }

    private boolean siCouleurGagneDansCetteDirection(GCouleur couleur, int idColonne, int idRangee, GDirection direction, int pourGagner) {

        for (int i = 0; i < pourGagner; i++) {

            int col = idColonne + direction.incrementHorizontal * i;
            int ran = idRangee + direction.incrementVertical * i;

            if (!siMemeCouleurCetteCase(couleur, col, ran)) {
                return false;
            }
        }

        return true;

    }

    private boolean siMemeCouleurCetteCase(GCouleur couleur, int idColonne, int idRangee){

        boolean colValide = getColonnes().size() > idColonne && idColonne >= 0;

        if (colValide) {

            List<GCouleur> jetons = colonnes.get(idColonne).getJetons();

            boolean ranValide = jetons.size() > idRangee && idRangee >= 0;

            if (ranValide) {
                return jetons.get(idRangee) == couleur;
            }

        }

        return false;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {

        throw new UnsupportedOperationException();

    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {

        throw new UnsupportedOperationException();

    }
}
