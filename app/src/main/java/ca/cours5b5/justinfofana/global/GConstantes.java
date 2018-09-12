package ca.cours5b5.justinfofana.global;

import android.util.Log;

import ca.cours5b5.justinfofana.vues.Vue;

public class GConstantes {

    static { Log.d("Atelier04", Vue.class.getSimpleName() + "::static"); }

    public static final int HAUTEUR_MAX = 10;
    public static final int HAUTEUR_MIN = 4;
    public static final int HAUTEUR_DEFAUT = 6;
    public static final int LARGEUR_MAX = 10;
    public static final int LARGEUR_MIN = 4;
    public static final int LARGEUR_DEFAUT = 7;
    public static final int SCORE_GAGNANT_MAX = 4;
    public static final int SCORE_GAGNANT_MIN = 3;
    public static final int SCORE_GAGNANT_DEFAUT = 4;
}