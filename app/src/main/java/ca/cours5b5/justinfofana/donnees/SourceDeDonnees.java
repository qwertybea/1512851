package ca.cours5b5.justinfofana.donnees;

import java.io.File;
import java.util.Map;

public abstract class SourceDeDonnees {
    public abstract Map<String, Object> chargerModele(final String cheminSauvegarde);
    public abstract void sauvegarderModele(final String cheminSauvegarde, final Map<String, Object> objetJson);
    public abstract void effacerModele(final String cheminSauvegarde);
    protected String getNomModele(String cheminSauvegarde) {
        return cheminSauvegarde.split(File.separator)[0];
    }
}
