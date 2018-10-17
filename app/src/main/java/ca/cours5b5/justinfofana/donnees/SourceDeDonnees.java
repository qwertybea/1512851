package ca.cours5b5.justinfofana.donnees;

import java.util.Map;

public interface SourceDeDonnees {
    public abstract Map<String, Object> chargerModele(final String cheminSauvegarde);
    public abstract void sauvegarderModele(final String cheminSauvegarde, final Map<String, Object> objetJson);
}
