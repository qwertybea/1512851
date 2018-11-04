package ca.cours5b5.justinfofana.controleurs;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.donnees.Disque;
import ca.cours5b5.justinfofana.donnees.Serveur;
import ca.cours5b5.justinfofana.donnees.SourceDeDonnees;
import ca.cours5b5.justinfofana.exceptions.ErreurModele;
import ca.cours5b5.justinfofana.global.DebugTools;
import ca.cours5b5.justinfofana.global.GLog;
import ca.cours5b5.justinfofana.modeles.MParametres;
import ca.cours5b5.justinfofana.modeles.MParametresPartie;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.modeles.Modele;
import ca.cours5b5.justinfofana.usagers.UsagerCourant;

public class ControleurModeles {

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;

    static {

        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();

        listeDeSauvegardes.add(Disque.getInstance());
        listeDeSauvegardes.add(Serveur.getInstance());

    }

    private ControleurModeles(){}

    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement) {
        ControleurModeles.sequenceDeChargement = sequenceDeChargement;
    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            Map<String, Object> objetJson = modele.enObjetJson();

            sourceDeDonnees.sauvegarderModele(getCheminSauvegarde(nomModele), objetJson);

        }

    }

    static Modele getModele(final String nomModele){

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){

            modele = chargerViaSequenceDeChargement(nomModele);

        }

        return modele;
    }

    private static Modele chargerViaSequenceDeChargement(final String nomModele){

        Modele modele = creerModeleSelonNom(nomModele);

        modelesEnMemoire.put(nomModele, modele);

        for(SourceDeDonnees sourceDeDonnees : sequenceDeChargement){

            Map<String, Object> objetJson = sourceDeDonnees.chargerModele(getCheminSauvegarde(nomModele));

            if(objetJson != null){

                GLog.activite(nomModele, sourceDeDonnees, objetJson);
                modele.aPartirObjetJson(objetJson);
                break;

            }

        }

        return modele;
    }

    private static Modele creerModeleSelonNom(String nomModele) {
        if(nomModele.equals(MParametres.class.getSimpleName())){

            return new MParametres();

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            MParametres mParametres = (MParametres) getModele(MParametres.class.getSimpleName());
            
            return new MPartie(mParametres.getParametresPartie().cloner());

        }else{

            throw new ErreurModele("Modèle inconnu: " + nomModele);

        }
    }

    public static void sauvegarderModele(String nomModele){

        for(SourceDeDonnees source : listeDeSauvegardes){

            sauvegarderModeleDansCetteSource(nomModele, source);

        }
    }

    public static void effacerModele(String nomModele){

        modelesEnMemoire.remove(nomModele);

        for(SourceDeDonnees source : listeDeSauvegardes){

            effacerModeleDansCetteSource(nomModele, source);

        }

    }


    public static void effacerModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        sourceDeDonnees.effacerModele(getCheminSauvegarde(nomModele));

    }

    private static String getCheminSauvegarde(String nomModele) {

        return nomModele + File.separator + UsagerCourant.getId();

//        String cheminSauvegarde = nomModele;
//
//        if (Serveur.class.getSimpleName().equals(nomModele)) {
//
//            if (UsagerCourant.siUsagerConnecte()) {
//                cheminSauvegarde += File.separator + UsagerCourant.getId();
//            }
//
//        }
//
//        return cheminSauvegarde;
    }
    /*
     * Le chemin est de la forme:
     *
     * nomModele/idUsager
     *
     * Par exemple:
     *
     * MPartie/T1m8GxiBAlhLUcF6Ne0GV06nnEg1
     *
     */

}
