package ca.cours5b5.justinfofana.donnees;

import java.io.File;
import java.util.Map;

public class Disque implements SourceDeDonnees {

    private static final Disque instance = new Disque();
    /*
     * L'instance unique du singleton
     *
     */

    public static Disque getInstance() {
        return instance;
    }

    private File repertoireRacine;

    public void setRepertoireRacine(File repertoireRacine) {
        this.repertoireRacine = repertoireRacine;
    }

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {


        return null;
    }
    /*
     * Retourne null s'il est impossible de charger le modèle
     *
     */

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {



    }
    /*
     * Affiche un avertissement dans le log s'il est impossible de sauvegarder
     * le modèle
     *
     */

    private File getFichier(String cheminSauvegarde) {
        return null;
    }
    /*
     * En général, le cheminSauvegarde est de la forme:
     *
     *     NomClasse/id
     *
     *     p.ex.
     *
     *     MParametres/usager23
     *     MParametres/usager12
     *
     *     ou pour l'usager par défaut:
     *
     *     MParametres
     *
     */

    private String getNomFichier(String nomModele) {
        return null;
    }


}
