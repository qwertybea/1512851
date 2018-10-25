package ca.cours5b5.justinfofana.global;

import android.util.Log;

public class GLog {

    private static final int INDICE_APPEL_A_AFFICHER = 4;

    private static final String SEPARATEUR_NOM_CLASSE = "\\.";

    private static final String PREFIXE = "GLog/";

    private static final String ETIQUETTE_ANDROID = PREFIXE + "andrd";
    private static final String ETIQUETTE_MVC = PREFIXE + "mvc";
    private static final String ETIQUETTE_VUE = PREFIXE + "vue";
    private static final String ETIQUETTE_MODELE = PREFIXE + "modele";
    private static final String ETIQUETTE_ACTIVITE = PREFIXE + "activite";
    private static final String ETIQUETTE_LISTENER = PREFIXE + "listener";
    private static final String ETIQUETTE_DONNEES = PREFIXE + "donnees";
    private static final String ETIQUETTE_AUTRE = PREFIXE + "autre";

    private static final int BORNE_FORMATTAGE_EN_HEX = 100000;

    public static void android(Object... valeursAdditionnelles){
        afficherMethode(ETIQUETTE_ANDROID, valeursAdditionnelles);
    }

    public static void mvc(Object... valeursAdditionnelles){
        afficherMethode(ETIQUETTE_MVC, valeursAdditionnelles);
    }

    public static void vue(Object... valeursAdditionnelles){
        afficherMethode(ETIQUETTE_VUE, valeursAdditionnelles);
    }

    public static void modele(Object... valeursAdditionnelles){
        afficherMethode(ETIQUETTE_MODELE, valeursAdditionnelles);
    }

    public static void activite(Object... valeursAdditionnelles){
        afficherMethode(ETIQUETTE_ACTIVITE, valeursAdditionnelles);
    }

    public static void listener(Object... valeursAdditionnelles){
        afficherMethode(ETIQUETTE_LISTENER, valeursAdditionnelles);
    }

    public static void donnees(Object... valeursAdditionnelles){
        afficherMethode(ETIQUETTE_DONNEES, valeursAdditionnelles);
    }

    public static void autre(Object... valeursAdditionnelles){
        afficherMethode(ETIQUETTE_AUTRE, valeursAdditionnelles);
    }


    private static void afficherMethode(String etiquette, Object... valeursAdditionnelles){

        StackTraceElement[] pileAppels = Thread.currentThread().getStackTrace();
        StackTraceElement appelAAfficher = pileAppels[INDICE_APPEL_A_AFFICHER];

        String fichierEtLigne = getFichierEtLigne(appelAAfficher);

        String nomMethode = getNomMethode(appelAAfficher);

        String nomClasseSimple = getNomClasseSimple(appelAAfficher);

        String chaineAppel = getChaineAppel(nomClasseSimple, nomMethode, fichierEtLigne, valeursAdditionnelles);

        Log.d(etiquette, chaineAppel);

    }

    private static String getFichierEtLigne(StackTraceElement appelAAfficher) {

        String retour = "  (";

        retour += appelAAfficher.getFileName();

        retour += ":";

        retour += appelAAfficher.getLineNumber();

        retour += ")";

        return retour;

    }

    private static String getNomMethode(StackTraceElement appelAAfficher){

        String nomMethode = appelAAfficher.getMethodName();

        return nomMethode;
    }

    private static String getNomClasseSimple(StackTraceElement appelAAfficher) {

        String nomClasseComplet = appelAAfficher.getClassName();

        String nomClasseSimple = getNomClasseSimple(nomClasseComplet);

        return nomClasseSimple;
    }

    private static String getNomClasseSimple(String nomClasseComplet){

        String[] segmentsDuNom = nomClasseComplet.split(SEPARATEUR_NOM_CLASSE);

        String nomClasseSimple = segmentsDuNom[segmentsDuNom.length - 1];

        return nomClasseSimple;

    }


    private static String getChaineAppel(String nomClasseSimple,
                                         String nomMethode,
                                         String fichierEtLigne,
                                         Object... valeursAdditionnelles){

        String appel = nomClasseSimple;

        appel += ".";

        appel += nomMethode;

        appel += "()";

        appel += getChaineValeursAdditionnelles(valeursAdditionnelles);

        appel += fichierEtLigne;

        return appel;
    }

    private static String getChaineValeursAdditionnelles(Object... valeursAdditionnelles){
        if(valeursAdditionnelles.length == 0){
            return "";
        }

        String chaineValeursAdditionnelles = "  [";

        for(int i=0; i < valeursAdditionnelles.length; i++){

            chaineValeursAdditionnelles += getChaineValeur(valeursAdditionnelles[i]);

            if(i<(valeursAdditionnelles.length-1)){

                chaineValeursAdditionnelles += ", ";

            }
        }

        chaineValeursAdditionnelles += "]";

        return chaineValeursAdditionnelles;
    }

    private static String getChaineValeur(Object valeur){
        if(valeur instanceof Integer){

            if((Integer) valeur >= BORNE_FORMATTAGE_EN_HEX){

                return String.format("0x%08X", valeur).toLowerCase();

            }else{

                return valeur.toString();
            }
        }

        else if(valeur instanceof Long
                || valeur instanceof Boolean
                || valeur instanceof Float
                || valeur instanceof Double
                || valeur instanceof String){

            return valeur.toString();

        }else if(valeur == null){

            return "null";

        }else{
            return valeur.getClass().getSimpleName();
        }
    }

}