package ca.cours5b5.justinfofana.donnees;

import java.io.File;
import java.util.Map;

public abstract class SourceDeDonnees {
    public abstract void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement);
    public abstract void sauvegarderModele(final String cheminSauvegarde, final Map<String, Object> objetJson);
    public abstract void effacerModele(final String cheminSauvegarde);

    protected String getNomModele(String cheminSauvegarde) {
        return cheminSauvegarde.split(File.separator)[0];
    }
}
