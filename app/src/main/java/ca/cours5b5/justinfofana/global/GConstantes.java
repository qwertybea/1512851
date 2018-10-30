package ca.cours5b5.justinfofana.global;

import android.util.Log;

import ca.cours5b5.justinfofana.vues.Vue;

public class GConstantes {

    static { Log.d("Atelier04", Vue.class.getSimpleName() + "::static"); }

    private GConstantes(){}

    public static final int LARGEUR_MIN = 4;
    public static final int LARGEUR_MAX = 10;
    public static final int LARGEUR_PAR_DEFAUT = 7;

    public static final int HAUTEUR_MIN = 4;
    public static final int HAUTEUR_MAX = 10;
    public static final int HAUTEUR_PAR_DEFAUT = 6;

    public static final int POUR_GAGNER_MIN = 3;
    public static final int POUR_GAGNER_PAR_DEFAUT = 4;

    public static final String EXTENSION_PAR_DEFAUT=".json";

    public static final int GRILLE_MARGIN = 5;

    public static final int RANGE_ENTETE = 0;
    public static final float ENTETE_POIDS_COLONNE = 1;
    public static final float ENTETE_POIDS_RANGE = 3;
    public static final float CASE_POIDS_COLONNE = 1;
    public static final float CASE_POIDS_RANGE = 1;


}
