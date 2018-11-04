package ca.cours5b5.justinfofana.usagers;

import com.google.firebase.auth.FirebaseAuth;

public class UsagerCourant {

    public static boolean siUsagerConnecte() {

        return FirebaseAuth.getInstance().getCurrentUser() != null;

    }
    /*
     * Retourne vrai si l'usager est connecté
     *
     * Utiliser FirebaseAuth
     *
     */

    public static String getId() {

        String id;

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            id = auth.getUid();
        } else {
            // TODO: find out what the default id is supposed to be
            id = "T1m8GxiBAlhLUcF6Ne0GV06nnEg1";
        }

        return id;
    }
    /*
     * Retourne l'identifiant de l'usager connecté
     * Sinon retourner un ID par défaut
     *
     * Utiliser FirebaseAuth
     *
     */

}
