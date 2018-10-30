package ca.cours5b5.justinfofana.modeles;

import java.util.Map;

import ca.cours5b5.justinfofana.exceptions.ErreurSerialisation;

public abstract class Modele {

    public abstract void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation;

    public abstract Map<String, Object> enObjetJson() throws ErreurSerialisation;

}
