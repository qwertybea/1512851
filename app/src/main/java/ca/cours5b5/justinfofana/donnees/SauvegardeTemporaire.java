package ca.cours5b5.justinfofana.donnees;

import android.os.Bundle;

import java.util.Map;

public class SauvegardeTemporaire implements SourceDeDonnees {

    private Bundle bundle;

    public SauvegardeTemporaire(Bundle bundle) {



    }

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {


        return null;
    }
    /*
     * Retourne null si le modèle n'est pas présent
     *
     */

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {



    }

}
