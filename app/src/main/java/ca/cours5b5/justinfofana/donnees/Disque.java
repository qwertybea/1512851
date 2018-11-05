package ca.cours5b5.justinfofana.donnees;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;

import ca.cours5b5.justinfofana.exceptions.ErreurModele;
import ca.cours5b5.justinfofana.global.GConstantes;
import ca.cours5b5.justinfofana.serialisation.Jsonification;

public class Disque extends SourceDeDonnees {

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
    public void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement) {

        File fichier = getFichier(cheminSauvegarde);

        try {

            String json = new String(Files.readAllBytes(fichier.toPath()));

            Map<String, Object> objetJson = Jsonification.enObjetJson(json);

            listenerChargement.reagirSucces(objetJson);

        } catch (FileNotFoundException e) {

            listenerChargement.reagirErreur(e);

        } catch (IOException e) {

            listenerChargement.reagirErreur(e);

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

    @Override
    public void effacerModele(String cheminSauvegarde) {

        File fichier = getFichier(cheminSauvegarde);

        try {

            fichier.delete();

        } catch (Exception e) {
            throw new ErreurModele(e);
        }

    }

    private File getFichier(String cheminSauvegarde) {

        String nomModele = getNomModele(cheminSauvegarde);

        String nomFichier = getNomFichier(nomModele);

        return new File(repertoireRacine, nomFichier);

    }
    /*
     * Obtenir le nomModele et l'utiliser pour le nom du fichier
     *
     * p.ex. MParametres/T1m8GxiBAlhLUcF6Ne0GV06nnEg1 => MParametres.json
     *
     */

    private String getNomFichier(String nomModele) {
        return nomModele + GConstantes.EXTENSION_PAR_DEFAUT;
    }


}
