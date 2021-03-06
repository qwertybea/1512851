package ca.cours5b5.justinfofana.global;

import android.util.Log;

import ca.cours5b5.justinfofana.vues.Vue;

public class GConstantes {

    static { Log.d("Atelier04", Vue.class.getSimpleName() + "::static"); }

    private GConstantes(){}

    // PARAMETRES
    public static final int LARGEUR_MIN = 4;
    public static final int LARGEUR_MAX = 10;
    public static final int LARGEUR_PAR_DEFAUT = 7;

    public static final int HAUTEUR_MIN = 4;
    public static final int HAUTEUR_MAX = 10;
    public static final int HAUTEUR_PAR_DEFAUT = 6;

    public static final int POUR_GAGNER_MIN = 3;
    public static final int POUR_GAGNER_PAR_DEFAUT = 4;

    // GRILLE view
    public static final int GRILLE_MARGIN = 5;

    public static final int RANGE_ENTETE = 0;
    public static final float ENTETE_POIDS_COLONNE = 1;
    public static final float ENTETE_POIDS_RANGE = 3;
    public static final float CASE_POIDS_COLONNE = 1;
    public static final float CASE_POIDS_RANGE = 1;

    // SAUVEGARDE + FILE
    public static final String EXTENSION_PAR_DEFAUT=".json";
    public static final int MA_CONSTANTE_CODE_CONNEXION = 123;

    // RESEAU
    public static final int NOMBRE_DE_VALEURS_A_CHARGER_DU_SERVEUR_PAR_DEFAUT = 10;

    public static final String CLE_ID_JOUEUR_HOTE = "idJoueurHote";
    public static final String CLE_ID_JOUEUR_INVITE = "idJoueurInvite";

    public static final String CLE_COUPS_JOUEUR_HOTE = "coupsJoueurHote";
    public static final String CLE_COUPS_JOUEUR_INVITE = "coupsJoueurInvite";

    // TODO: remplacer les IDs par ceux de vos 2 usagers de test
    // hote :   jf.ecolier
    // invite : indigo
    public static final String FIXME_JSON_PARTIE_RESEAU = "{\"listeCoups\":[],\"parametres\":{\"largeur\":\"7\",\"pourGagner\":\"4\",\"hauteur\":\"6\"},\"idJoueurInvite\":\"sP3V1vIdF5eeI01FAkw10iTPuX32\",\"idJoueurHote\":\"J7FTKo3jd5gvAUO7Kdp5MaQyou02\"}";


}
