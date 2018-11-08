package ca.cours5b5.justinfofana.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.exceptions.ErreurSerialisation;
import ca.cours5b5.justinfofana.global.GCouleur;

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

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {

        throw new UnsupportedOperationException();

    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {

        throw new UnsupportedOperationException();

    }
}
