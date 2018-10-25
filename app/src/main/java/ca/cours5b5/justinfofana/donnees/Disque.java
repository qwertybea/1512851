package ca.cours5b5.justinfofana.donnees;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;

import ca.cours5b5.justinfofana.global.GConstantes;
import ca.cours5b5.justinfofana.serialisation.Jsonification;

public class Disque implements SourceDeDonnees {

    private static final Disque instance = new Disque();

    private File repertoireRacine;

    private Disque() {}

    public static Disque getInstance() {
        return instance;
    }

    public void setRepertoireRacine(File repertoireRacine) {
        this.repertoireRacine = repertoireRacine;
    }

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {

        File fichier = getFichier(cheminSauvegarde);

        try {

            String json = new String(Files.readAllBytes(fichier.toPath()));

            Map<String, Object> objetJson = Jsonification.enObjetJson(json);

            return objetJson;

        } catch (FileNotFoundException e) {

            return null;

        } catch (IOException e) {

            return null;

        }
    }
    /*
     * Retourne null s'il est impossible de charger le modèle
     *
     */

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {

        File fichier = getFichier(cheminSauvegarde);

        String json = Jsonification.enChaine(objetJson);

        try {

            OutputStream outputStream = new FileOutputStream(fichier);

            outputStream.write(json.getBytes());

            outputStream.close();

        } catch (FileNotFoundException e) {

            Log.d("Atelier07", "File not found: " + cheminSauvegarde);

        } catch (IOException e) {

            Log.d("Atelier07", "IOException: " + cheminSauvegarde);

        }

    }
    /*
     * Affiche un avertissement dans le log s'il est impossible de sauvegarder
     * le modèle
     *
     */

    private File getFichier(String cheminSauvegarde) {

        String nomFichier = getNomFichier(cheminSauvegarde);

        return new File(repertoireRacine, nomFichier);

    }

    private String getNomFichier(String nomModele) {
        return nomModele + GConstantes.EXTENSION_PAR_DEFAUT;
    }


}
