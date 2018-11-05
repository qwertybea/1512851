package ca.cours5b5.justinfofana.controleurs;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.justinfofana.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.justinfofana.donnees.Disque;
import ca.cours5b5.justinfofana.donnees.ListenerChargement;
import ca.cours5b5.justinfofana.donnees.Serveur;
import ca.cours5b5.justinfofana.donnees.SourceDeDonnees;
import ca.cours5b5.justinfofana.exceptions.ErreurModele;
import ca.cours5b5.justinfofana.global.DebugTools;
import ca.cours5b5.justinfofana.global.GLog;
import ca.cours5b5.justinfofana.modeles.MParametres;
import ca.cours5b5.justinfofana.modeles.MParametresPartie;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.modeles.Modele;
import ca.cours5b5.justinfofana.serialisation.Jsonification;
import ca.cours5b5.justinfofana.usagers.UsagerCourant;

public class ControleurModeles {

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;

    static {

        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();

    }

    private ControleurModeles(){}

    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement) {
        ControleurModeles.sequenceDeChargement = sequenceDeChargement;

        for (SourceDeDonnees sourceDeDonnees : sequenceDeChargement) {

            listeDeSauvegardes.add(sourceDeDonnees);

        }
    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            Map<String, Object> objetJson = modele.enObjetJson();

            sourceDeDonnees.sauvegarderModele(getCheminSauvegarde(nomModele), objetJson);

        }

    }

    static void getModele(final String nomModele, ListenerGetModele listenerGetModele){

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){

            creerModeleEtChargerDonnees(nomModele, listenerGetModele);

        } else {

            listenerGetModele.reagirAuModele(modele);

        }

    }

    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {
        if(nomModele.equals(MParametres.class.getSimpleName())){

            MParametres mParametres = new MParametres();

            listenerGetModele.reagirAuModele(mParametres);

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            // TODO: see if this is ok

            MPartie mPartie = new MPartie(new MParametresPartie());

            listenerGetModele.reagirAuModele(mPartie);

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




    /***** ajouts  *******
     *
     * Voir schéma ci-bas
     * Attention aux trois conditions
     * Attention à la méthode creerModeleEtChargerDonnees qui doit aussi mémoriser le modèle
     *
     */


    private static void creerModeleEtChargerDonnees(final String nomModele,
                                                    final ListenerGetModele listenerGetModele) {

        creerModeleSelonNom(nomModele, listenerGetModele);

    }
    /*
     * Aussi: mémoriser le modèle dans modelesEnMemoire
     */


//    private static void chargerDonnees(Modele modele,
    public static void chargerDonnees(Modele modele,
            String nomModele,
            ListenerGetModele listenerGetModele) {

        GLog.donnees(nomModele);

        chargementViaSequence(modele, getCheminSauvegarde(nomModele), listenerGetModele, 0);

    }

    private static void chargementViaSequence(Modele modele,
            String cheminDeSauvegarde,
            ListenerGetModele listenerGetModele,
            int indiceSourceCourante){

        if (indiceSourceCourante == listeDeSauvegardes.size()) {

            terminerChargement(modele, listenerGetModele);

        } else {

            chargementViaSourceCouranteOuSuivante(modele, cheminDeSauvegarde, listenerGetModele, indiceSourceCourante);

        }

    }

    private static void chargementViaSourceCouranteOuSuivante(final Modele modele,
    final String cheminDeSauvegarde,
    final ListenerGetModele listenerGetModele,
    final int indiceSourceCourante) {

        listeDeSauvegardes.get(indiceSourceCourante).chargerModele(cheminDeSauvegarde, new ListenerChargement() {
            @Override
            public void reagirSucces(Map<String, Object> objetJson) {

                terminerChargementAvecDonnees(objetJson, modele, listenerGetModele);

                GLog.donnees("Succes", listeDeSauvegardes.get(indiceSourceCourante), indiceSourceCourante);

                GLog.donnees(Jsonification.enChaine(objetJson));

            }

            @Override
            public void reagirErreur(Exception e) {

                chargementViaSourceSuivante(modele, cheminDeSauvegarde, listenerGetModele, indiceSourceCourante);

                GLog.donnees("Echec", listeDeSauvegardes.get(indiceSourceCourante), indiceSourceCourante);

            }
        });

    }

    private static void terminerChargementAvecDonnees(Map<String, Object> objetJson,
            Modele modele,
            ListenerGetModele listenerGetModele) {

        modele.aPartirObjetJson(objetJson);

        terminerChargement(modele, listenerGetModele);

    }

    private static void terminerChargement(Modele modele,
            ListenerGetModele listenerGetModele) {

        listenerGetModele.reagirAuModele(modele);

    }

    private static void chargementViaSourceSuivante(Modele modele,
            String cheminDeSauvegarde,
            ListenerGetModele listenerGetModele,
            int indiceSourceCourante) {

            chargementViaSequence(modele, cheminDeSauvegarde, listenerGetModele, indiceSourceCourante + 1);

    }

}
