package ca.cours5b5.justinfofana.donnees;

import android.os.Bundle;

import java.util.Map;

import ca.cours5b5.justinfofana.serialisation.Jsonification;

public class SauvegardeTemporaire extends SourceDeDonnees {

    private Bundle bundle;

    public SauvegardeTemporaire(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {
        if(bundle != null && bundle.containsKey(getCle(cheminSauvegarde))){

            String json = bundle.getString(getCle(cheminSauvegarde));

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
            bundle.putString(getCle(cheminSauvegarde), json);

        }
    }

    @Override
    public void effacerModele(String cheminSauvegarde) {

        if(bundle != null){

            bundle.remove(getCle(cheminSauvegarde));

        }

    }

    private String getCle(String cheminSauvegarde){
        return getNomModele(cheminSauvegarde);
    }
    /*
     * Utiliser le nomModele comme clé de sauvegarde
     *
     * P.ex: MPartie/T1m8GxiBAlhLUcF6Ne0GV06nnEg1 => MPartie
     *
     */

}
