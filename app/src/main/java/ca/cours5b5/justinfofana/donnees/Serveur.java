package ca.cours5b5.justinfofana.donnees;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

import ca.cours5b5.justinfofana.global.GLog;

public class Serveur extends SourceDeDonnees {

    private Serveur(){}

    private static final Serveur instance = new Serveur();

    public static Serveur getInstance() {
        return instance;
    }


    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {

        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

        noeud.setValue(objetJson);

    }
    /*
     * Sauvegarder sur le serveur
     *
     * Utiliser FirebaseDatabase et DatabaseReference
     *
     */


    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {

        return null;
    }
    /*
     * BONUS: est-ce possible d'implanter cette méthode avec cette signature?
     */

    @Override
    public void effacerModele(String cheminSauvegarde) {

        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

        noeud.removeValue();

    }
    /*
     * BONUS
     */

}
