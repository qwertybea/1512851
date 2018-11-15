package ca.cours5b5.justinfofana.controleurs;

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
import ca.cours5b5.justinfofana.global.GLog;
import ca.cours5b5.justinfofana.modeles.Identifiable;
import ca.cours5b5.justinfofana.modeles.MParametres;
import ca.cours5b5.justinfofana.modeles.MPartie;
import ca.cours5b5.justinfofana.modeles.Modele;
import ca.cours5b5.justinfofana.serialisation.Jsonification;
import ca.cours5b5.justinfofana.usagers.UsagerCourant;

public final class ControleurModeles {

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;

    static {

        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();

        listeDeSauvegardes.add(Serveur.getInstance());
        listeDeSauvegardes.add(Disque.getInstance());

    }

    private ControleurModeles(){}

    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement) {
        ControleurModeles.sequenceDeChargement = sequenceDeChargement;
    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            Map<String, Object> objetJson = modele.enObjetJson();

            GLog.donnees(sourceDeDonnees, modele, getCheminSauvegarde(nomModele), Jsonification.enChaine(objetJson));

            sourceDeDonnees.sauvegarderModele(getCheminSauvegarde(nomModele), objetJson);

        }

    }

    static void getModele(final String nomModele, final ListenerGetModele listenerGetModele){

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){

            creerModeleEtChargerDonnees(nomModele, new ListenerGetModele() {

                @Override
                public void reagirAuModele(Modele modele) {

                    listenerGetModele.reagirAuModele(modele);

                }

            });

        } else {

            listenerGetModele.reagirAuModele(modele);

        }

    }

    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {
        if(nomModele.equals(MParametres.class.getSimpleName())){

            MParametres mParametres = new MParametres();

            listenerGetModele.reagirAuModele(mParametres);

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {

                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres mParametres = (MParametres) modele;

                    MPartie mPartie = new MPartie(mParametres.getParametresPartie());

                    listenerGetModele.reagirAuModele(mPartie);

                }

            });

        }else{

            throw new ErreurModele("Modèle inconnu: " + nomModele);

        }
    }

    public static void sauvegarderModele(String nomModele){

        GLog.activite(nomModele, listeDeSauvegardes.size());

        for(SourceDeDonnees source : listeDeSauvegardes){

            sauvegarderModeleDansCetteSource(nomModele, source);

        }
    }

    public static void effacerModele(String nomModele){

        ControleurObservation.detruireObservation(modelesEnMemoire.get(nomModele));

        modelesEnMemoire.remove(nomModele);

        for(SourceDeDonnees source : listeDeSauvegardes){

            effacerModeleDansCetteSource(nomModele, source);

        }

    }


    public static void effacerModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        sourceDeDonnees.effacerModele(getCheminSauvegarde(nomModele));

    }

    private static String getCheminSauvegarde(String nomModele) {

        String cheminSauvegarde = nomModele;

        Modele modele = modelesEnMemoire.get(nomModele);

        if (modele != null && modele instanceof Identifiable) {

            cheminSauvegarde += File.separator + ((Identifiable) modele).getId();

        } else {

            cheminSauvegarde += File.separator + UsagerCourant.getId();

        }

        return cheminSauvegarde;
    }
    /*
     * si le modèle est Identifiable, alors le chemin est nomModele/idModele
     * sinon, le chemin est nomModele/idUsager
     *
     */

    private static void creerModeleEtChargerDonnees(final String nomModele,
                                                    final ListenerGetModele listenerGetModele) {

        creerModeleSelonNom(nomModele, new ListenerGetModele() {

            @Override
            public void reagirAuModele(Modele modele) {

                modelesEnMemoire.put(nomModele, modele);

                chargerDonnees(modele, nomModele, new ListenerGetModele() {

                    @Override
                    public void reagirAuModele(Modele modele) {

                        listenerGetModele.reagirAuModele(modele);

                    }

                });

            }

        });

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

        GLog.activite(indiceSourceCourante, sequenceDeChargement.length);

        if (indiceSourceCourante == sequenceDeChargement.length) {

            terminerChargement(modele, listenerGetModele);

        } else {

            chargementViaSourceCouranteOuSuivante(modele, cheminDeSauvegarde, listenerGetModele, indiceSourceCourante);

        }

    }

    private static void chargementViaSourceCouranteOuSuivante(final Modele modele,
    final String cheminDeSauvegarde,
    final ListenerGetModele listenerGetModele,
    final int indiceSourceCourante) {

        sequenceDeChargement[indiceSourceCourante].chargerModele(cheminDeSauvegarde, new ListenerChargement() {
            @Override
            public void reagirSucces(Map<String, Object> objetJson) {

                terminerChargementAvecDonnees(objetJson, modele, listenerGetModele);

                GLog.donnees("Succes", cheminDeSauvegarde, sequenceDeChargement[indiceSourceCourante], indiceSourceCourante);

                GLog.donnees(Jsonification.enChaine(objetJson));

            }

            @Override
            public void reagirErreur(Exception e) {

                chargementViaSourceSuivante(modele, cheminDeSauvegarde, listenerGetModele, indiceSourceCourante);

                GLog.donnees("Echec", cheminDeSauvegarde, sequenceDeChargement[indiceSourceCourante], indiceSourceCourante);

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
