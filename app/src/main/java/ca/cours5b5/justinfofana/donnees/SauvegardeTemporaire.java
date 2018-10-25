package ca.cours5b5.justinfofana.donnees;

import android.os.Bundle;

import java.util.Map;

import ca.cours5b5.justinfofana.serialisation.Jsonification;

public class SauvegardeTemporaire implements SourceDeDonnees {

    private Bundle bundle;

    public SauvegardeTemporaire(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {
        if(bundle != null && bundle.containsKey(cheminSauvegarde)){

            String json = bundle.getString(cheminSauvegarde);

            Map<String, Object> objetJson = Jsonification.enObjetJson(json);

            return objetJson;

        }else{

            return null;

        }
    }
    /*
     * Retourne null si le modèle n'est pas présent
     *
     */

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        if(bundle != null){

            String json = Jsonification.enChaine(objetJson);
            bundle.putString(cheminSauvegarde, json);

        }
    }

}
