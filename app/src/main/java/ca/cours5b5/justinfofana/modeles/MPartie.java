package ca.cours5b5.justinfofana.modeles;

import java.util.Map;

import ca.cours5b5.justinfofana.exceptions.ErreurSerialisation;
import ca.cours5b5.justinfofana.serialisation.AttributSerialisable;

public class MPartie extends Modele {

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";


    public MPartie(MParametresPartie parametres) {
        this.parametres = parametres;
    }

    public MParametresPartie getParametres() {
        return this.parametres;
    }


    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {}
    /*
     * Inutilisé pour l'instant
     *
     */

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {return null;}
    /*
     * Inutilisé pour l'instant
     *
     */

}
